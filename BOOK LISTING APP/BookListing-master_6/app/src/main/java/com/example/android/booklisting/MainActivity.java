package com.example.android.booklisting;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends MenuActivity {
    /**
     * Variables for storing Book's information
     */
    private String title;
    private String authorsString = "";
    private String publisher;
    private String pageCount;
    private String description;
    private int averageRating;
    private String publishedDate;
    private String isbnType;
    private String isbnValue;
    private String bookImageResourceURL;
    private ArrayList<Books> mBooks;
    /**
     * Create Book's Object for storing info of the clicked Book from the ListView
     */
    Books booksObject = new Books();
    /**
     * Save the user input in this variable
     */
    private String searchQuery;
    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    /**
     * URL to query the GoogleBook dataset for book's information
     */
    private static String BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    //http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-12-01&minmagnitude=7

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Find the Search Button
        Button search = (Button) findViewById(R.id.search);
        //Set click Listener on Search Button Click
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Find the edit text's actual text and make it compatible for a url search query
                searchQuery = ((EditText) findViewById(R.id.searchQuery)).getText().toString().replace(" ", "+");
                //Check if user input is empty or it contains some query text
                if (searchQuery.isEmpty()) {
                    Context context = getApplicationContext();
                    String text = "Nothing Entered in Search";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                } else {
                    // String to be attached to the BOOK_REQUEST_URL
                    String appendableQuery = searchQuery + "&key=AIzaSyAN16J8Zakn2RQbFV4iBB8JrFPnFIev2wE&maxResults=10&country=IN";
                    BOOK_REQUEST_URL += appendableQuery; //final value of "URL for Google Book API"
                    BookAsyncTask task = new BookAsyncTask();
                    //If network is available then perform the further task of AsynckTask calling
                    if (isNetworkAvailable()) {
                        // Kick off an {@link AsyncTask} to perform the network request
                        task.execute();
                    } else {
                        Toast.makeText(getApplicationContext(), "Network not available", Toast.LENGTH_SHORT).show();
                        //Reset the to the original URL to prevent app crash
                        BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
                    }
                }
            }
        });
    }

    //Check if network is available or not
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Update the screen to display information from the given {@link Books}.
     */
    private void updateUi(final ArrayList<Books> book) {
        final ListView listView = (ListView) findViewById(R.id.list);
        BooksAdapter booksAdapter = new BooksAdapter(MainActivity.this, book);
        listView.setAdapter(booksAdapter);
        /*Setting click listener on ListView*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Books bookses = book.get(position);
                Books bookses = book.get(position);
                Intent booksIntent = new Intent(getApplicationContext(), BookDetailsActivity.class);
                booksIntent.putExtra("booksObjectBundle", bookses);
                startActivity(booksIntent);
            }
        });
    }

    /**
     * Save the state on rotation of device
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putParcelableArrayList("booksObjectBundle", mBooks);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Save the state on rotation of device
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mBooks = savedInstanceState.getParcelableArrayList("booksObjectBundle");
            BooksAdapter adapter = new BooksAdapter(this, mBooks);
            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    //Books bookses = book.get(position);
                    Books bookses = mBooks.get(position);
                    Intent booksIntent = new Intent(getApplicationContext(), BookDetailsActivity.class);
                    booksIntent.putExtra("booksObjectBundle", bookses);
                    startActivity(booksIntent);
                }
            });
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread, and then
     * update the UI with the first earthquake in the response.
     */
    private class BookAsyncTask extends AsyncTask<URL, Void, ArrayList<Books>> {
        @Override
        protected ArrayList<Books> doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(BOOK_REQUEST_URL);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            // Extract relevant fields from the JSON response and create an {@link Books} object
            ArrayList<Books> book = extractFeatureFromJson(jsonResponse);

            // Return the {@link Books} object as the result fo the {@link BookAsyncTask}
            return book;
        }

        /**
         * Update the screen with the given book (which was the result of the
         * {@link BookAsyncTask}).
         */
        @Override
        protected void onPostExecute(ArrayList<Books> book) {
            if (book == null) {
                return;
            }
            mBooks = book;
            updateUi(book);
            //Make the EditText go Blank after the queried search is fetched
            EditText editText = (EditText) findViewById(R.id.searchQuery);
            editText.setText(null);
            BOOK_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";  //Reset the to the original URL
        }

        /**
         * Returns new URL object from the given string URL.
         */
        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException exception) {
                Toast.makeText(MainActivity.this, "Error Creating URL", Toast.LENGTH_SHORT).show();
                return null;
            }
            return url;
        }

        /**
         * Make an HTTP request to the given URL and return a String as the response.
         */
        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";
            if (url == null) {
                return jsonResponse;
            }
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.connect();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Toast.makeText(MainActivity.this, "Error Response Code: "
                            + urlConnection.getResponseCode(), Toast.LENGTH_SHORT).show();
                }
            } catch (IOException e) {
                // TODO: Handle the exception
                Toast.makeText(MainActivity.this, "There is an IO exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    // function must handle java.io.IOException here
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} into a String which contains the
         * whole JSON response from the server.
         */
        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Return an {@link Books} object by parsing out information
         * about the first earthquake from the input googleBooksJSON string.
         */
        private ArrayList<Books> extractFeatureFromJson(String googleBooksJSON) {
            try {
                ArrayList<Books> arrayListOfBooks = new ArrayList<Books>();
                JSONObject baseJsonResponse = new JSONObject(googleBooksJSON);
                //Check if the base/root JSONObject has the desired "key" of value "items" and then only proceed
                if (baseJsonResponse.has("items")) {
                    JSONArray itemsArray = baseJsonResponse.getJSONArray("items");
                    if (itemsArray.length() > 0) {
                        for (int i = 0; i < itemsArray.length(); i++) {
                            JSONObject volumeInfo = itemsArray.getJSONObject(i).getJSONObject("volumeInfo");
                            //Check if the JSONObject volumeInfo has the desired string with value "title" and then only proceed
                            if (volumeInfo.has("title")) {
                                title = volumeInfo.getString("title");
                                booksObject.setTitle(title);
                            }
                            //Check if the JSONObject volumeInfo has the desired string with value "authors" and then only proceed
                            if (volumeInfo.has("authors")) {
                                JSONArray authors = volumeInfo.getJSONArray("authors");
                                //get author(s) name
                                authorsString = "";
                                for (int j = 0; j < authors.length(); j++) {
                                    String author = authors.getString(j);
                                    if (author.isEmpty()) {
                                        authorsString = "N/A";
                                    } else if (j == (authors.length() - 1)) {
                                        authorsString = authorsString + " and " + author;
                                        /**Else concatenate the author with a comma
                                         * ( these are all the iterations between the first and final )**/
                                    } else {
                                        authorsString = authorsString + ", " + author;
                                    }
                                }
                                booksObject.setAuthor(authorsString);
                            }
                            //get publisher(s) name
                            if (volumeInfo.has("publisher")) {
                                publisher = volumeInfo.getString("publisher");
                                booksObject.setPublisher(publisher);
                            }
                            //get page count of the book
                            if (volumeInfo.has("pageCount")) {
                                pageCount = volumeInfo.getString("pageCount");
                                booksObject.setPageCount(pageCount);
                            }
                            //get description of the book
                            if (volumeInfo.has("description")) {
                                description = volumeInfo.getString("description");
                                booksObject.setDescription(description);
                            }
                            //get ratings of the book
                            if (volumeInfo.has("averageRating")) {
                                averageRating = volumeInfo.getInt("averageRating");
                                booksObject.setRatings(averageRating);
                            }
                            //get publication date of the book
                            if (volumeInfo.has("publishedDate")) {
                                publishedDate = volumeInfo.getString("publishedDate");
                                booksObject.setPublishedDate(publishedDate);
                            }
                            //get ISBN details of the book
                            if (volumeInfo.has("industryIdentifiers")) {
                                JSONArray industryIdentifiers = volumeInfo.getJSONArray("industryIdentifiers");
                                JSONObject firstISBN = industryIdentifiers.getJSONObject(0);
                                isbnType = firstISBN.getString("type");
                                isbnValue = firstISBN.getString("identifier");
                                booksObject.setISBNType(isbnType);
                                booksObject.setISBNValue(isbnValue);
                            }
                            //get thumbnails of the book
                            if (volumeInfo.has("imageLinks")) {
                                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                                if (imageLinks.has("thumbnail")) {
                                    bookImageResourceURL = imageLinks.getString("thumbnail");
                                    booksObject.setBookImageResourceURL(bookImageResourceURL);
                                }
                                else if (imageLinks.has("smallThumbnail")) {
                                    bookImageResourceURL = imageLinks.getString("smallThumbnail");
                                    booksObject.setBookImageResourceURL("smallThumbnail");
                                }
                            }

                            //Put all these books info in a custom ArrayList
                            arrayListOfBooks.add(new Books(booksObject.getTitle(), booksObject.getAuthor(), booksObject.getPublisher(),
                                    booksObject.getPageCount(), booksObject.getDescription(), booksObject.getRatings(),
                                    booksObject.getPublishedDate(), booksObject.getISBNType(),
                                    booksObject.getISBNValue(), booksObject.getBookImageResourceURL()));
                        }
                    }
                    return arrayListOfBooks;
                } else
                    Toast.makeText(MainActivity.this, "No Book found, search again", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, "Problem parsing the Google Books JSON results" + e, Toast.LENGTH_SHORT).show();
            }
            return null;
        }
    }
}
