package app.com.ttins.booklist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import app.com.ttins.booklist.Adapter.BookListAdapter;
import app.com.ttins.booklist.Gson.Book;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    SearchView searchView;
    ListView listView;
    TextView emptyListTextView;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ArrayList<Book> bookList = new ArrayList<>();
    BookListAdapter bookListArrayAdapter;
    FloatingActionButton searchBookFab;
    boolean isSearchOpened = false;
    Bitmap fabIcon;

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putParcelableArrayList(
                getString(R.string.book_activity_saved_instance_booklist_key),
                bookList);
        savedInstanceState.putBoolean(
                getString(R.string.book_activity_saved_instance_book_search_key), isSearchOpened);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView = (SearchView) findViewById(R.id.search_view_main_activity);
        listView = (ListView) findViewById(R.id.book_list_view_main_activity);
        emptyListTextView = (TextView) findViewById(R.id.empty_book_list_view_main_activity);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.app_name));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorTextIcons));
        searchBookFab = (FloatingActionButton) findViewById(R.id.search_book_fab);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            listView.setNestedScrollingEnabled(true);
        }

        searchBookFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSearchOpened) {
                    hideSearchView();
                } else {
                    showSearchView();
                }
            }
        });

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /* At Startup hide the Book List */
        listView.setEmptyView(emptyListTextView);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(getString(R.string.book_activity_saved_instance_booklist_key))) {
                bookList = savedInstanceState
                        .getParcelableArrayList(getString(R.string.book_activity_saved_instance_booklist_key));
                updateBookList();
            }

            if (savedInstanceState.containsKey(getString(R.string.book_activity_saved_instance_book_search_key))) {
                isSearchOpened = savedInstanceState
                        .getBoolean(getString(R.string.book_activity_saved_instance_book_search_key));
                if (isSearchOpened) {
                    showSearchView();
                }
            }
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d(LOG_TAG, "setOnQueryTextListener: " + query);
                hideSearchView();
                isSearchOpened = false;
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    Log.d(LOG_TAG, "Network connected...");
                    AsyncTask bookAsyncTask = new BooksHttpRequestAsyncTask();
                    String[] queryParam = {query};
                    bookAsyncTask.execute(queryParam);
                } else {
                    Log.d(LOG_TAG, "Network offline");
                    showShortToast(getString(R.string.network_unavailable_message));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void showShortToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void hideSearchView() {
        searchView.setVisibility(View.GONE);
        fabIcon = BitmapFactory.decodeResource(getResources(),
                android.support.design.R.drawable.abc_ic_search_api_mtrl_alpha);
        searchBookFab.setImageBitmap(fabIcon);
        isSearchOpened = false;
    }

    private void showSearchView() {
        searchView.setVisibility(View.VISIBLE);
        fabIcon = BitmapFactory.decodeResource(getResources(),
                android.support.design.R.drawable.abc_ic_clear_mtrl_alpha);
        searchBookFab.setImageBitmap(fabIcon);
        isSearchOpened = true;
    }

    public class BooksHttpRequestAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            Log.d(LOG_TAG, "doInBackground: " + params[0]);
            try {
                bookHttpRequest(params[0]);
            } catch (IOException e) {
                Log.d(LOG_TAG, "Error on Http Request");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            updateBookList();
        }
    }

    private void updateBookList() {
        bookListArrayAdapter = new BookListAdapter(getBaseContext(), bookList);
        listView.setAdapter(bookListArrayAdapter);
        bookListArrayAdapter.notifyDataSetChanged();
    }

    private void bookHttpRequest(String query) throws IOException {
        Log.d(LOG_TAG, "bookHttpRequest: " + query);
        InputStream inputStream = null;

        try {
            String encodedQuery = URLEncoder.encode(query, "utf-8");
            URL url = new URL(getResources().getString(R.string.http_query_address) + encodedQuery +
                    getResources().getString(R.string.query_projection_key) +
                    getResources().getString(R.string.projection_lite_key));

            Log.d(LOG_TAG, "url: " + url.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(getResources().getInteger(R.integer.http_read_timeout));
            httpURLConnection.setConnectTimeout(getResources().getInteger(R.integer.http_connect_timeout));
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();

            int response = httpURLConnection.getResponseCode();
            Log.d(LOG_TAG, "Response code is: " + response);

            switch (response) {
                case HttpURLConnection.HTTP_OK:
                    inputStream = httpURLConnection.getInputStream();
                    String stringResponse = readIt(inputStream);
                    parseJson(stringResponse);
                    break;
                case HttpURLConnection.HTTP_NOT_FOUND:
                    showShortToast(getString(R.string.http_url_not_found_message));
                    break;
                default:
                    showShortToast(getString(R.string.http_generic_error_message));
                    break;
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public void parseJson(String stringFromInputStream) {
        try {
            bookList.clear();
            JSONObject jsonObject = new JSONObject(stringFromInputStream);
            JSONArray jArray = jsonObject.getJSONArray(getString(R.string.json_items_key));

            for(int i = 0; i < jArray.length(); i++) {
                ArrayList<String> authorList = new ArrayList<>();
                Book book = new Book();

                JSONObject volumeInfo = jArray.getJSONObject(i)
                        .getJSONObject(getString(R.string.json_volume_info_key));
                String title = volumeInfo.getString(getString(R.string.json_title_key));
                book.setTitle(title);
                JSONArray authors = volumeInfo.getJSONArray(getString(R.string.json_authors_key));

                for (int j = 0; j < authors.length(); j++) {
                    String author = authors.getString(j);
                    authorList.add(author);
                }

                book.setAuthors(authorList);
                bookList.add(book);
            }

        } catch (JSONException e) {
            Log.d(LOG_TAG, "JSONException");
            e.printStackTrace();
        }
    }

    public String readIt(InputStream stream) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader responseReader = new BufferedReader(new InputStreamReader(stream));
        String line = responseReader.readLine();

        while (line != null){
            builder.append(line);
            line = responseReader.readLine();
        }

        return builder.toString();
    }
}
