package com.example.asheransari.booknew;

/**
 * Created by asher.ansari on 10/13/2016.
 */
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FetchBookTask extends AsyncTask<String, Void, ArrayList<Book>> {

    private final String LOG_TAG = FetchBookTask.class.getSimpleName();

    private BookAdapter mBookAdapter;

    public FetchBookTask(BookAdapter bookAdapter) {
        this.mBookAdapter = bookAdapter;
    }

    @Override
    protected ArrayList<Book> doInBackground(String... params) {

        if (params.length == 0) {
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String rawJson = null;

        try {

            final String GOOGLE_BOOK_API = "https://www.googleapis.com/books/v1/volumes?";
            final String QUERY_PARAM = "q";

            final Uri builtUri = Uri.parse(GOOGLE_BOOK_API).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, params[0])
                    .build();

            final URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            final InputStream inputStream = urlConnection.getInputStream();
            final StringBuilder buffer = new StringBuilder();

            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }

            if (buffer.length() == 0) {
                return null;
            }

            rawJson = buffer.toString();

        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return parseJson(rawJson);
    }

    private ArrayList<Book> parseJson(String rawJson) {

        final ArrayList<Book> books = new ArrayList<>();

        final String TITLE = "title";
        final String AUTHORS = "authors";
        final String ITEM_LIST = "items";
        final String VOLUME_INFO = "volumeInfo";

        try {
            final JSONObject booksJson = new JSONObject(rawJson);
            final JSONArray itemArray = booksJson.getJSONArray(ITEM_LIST);

            for (int i = 0; i < itemArray.length(); i++) {

                final JSONObject item = itemArray.getJSONObject(i);
                final JSONObject bookVolumeInfo = item.getJSONObject(VOLUME_INFO);

                final String title = bookVolumeInfo.getString(TITLE);
                final StringBuilder authors = new StringBuilder();
                if (bookVolumeInfo.has(AUTHORS)) {


                    final JSONArray authorArray = bookVolumeInfo.getJSONArray(AUTHORS);

                    for (int j = 0; j < authorArray.length(); j++) {
                        authors.append(authorArray.getString(j));

                        if (j < authorArray.length() - 1) {
                            authors.append(", ");
                        }
                    }
                }

                books.add(new Book(title, authors.toString()));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }

        return books;
    }

    @Override
    protected void onPostExecute(ArrayList<Book> books) {
        if (books != null && mBookAdapter != null) {
            mBookAdapter.clear();
            for (Book book : books) {
                mBookAdapter.add(book);
            }
        }
    }
}
