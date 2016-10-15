package com.mlgrier.booklistingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String API_KEY = "&key=AIzaSyD1XEZaVzDVLpFOLL5vsGbHEQGH32klEhg";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    //Declaring userBookSearch as a String Variable
    public String userBookSearch;
    //Declaring userInput as a EditText Variable
    private EditText userInput;
    //Declaring searchButton as a Button Variable
    private Button searchButton;
    //Declaring tempBookArrayList is = to 0
    private ArrayList<Book> tempBookArrayList = null;



    static class ViewHolderItem {
        ImageView bookImage;
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookPublisher;
        TextView bookCategory;
        RatingBar bookRating;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView bookListView = (ListView) findViewById(R.id.list);

        //collecting the book keyword search here and putting it in userInput
        userInput = (EditText) findViewById(R.id.editText);

        //connecting the button to collect input info on click
        searchButton = (Button) findViewById(R.id.bookSearchBtn);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //storing the text in a string called userBookSearch/ .replaceAll,
                //added to be able to search for multiple words
                userBookSearch = userInput.getText().toString().replaceAll(" ", "+");
                //Logging the search term the user entered
                Log.v(LOG_TAG, userBookSearch);
                //if user doesn't enter a search term a toast will show,
                //and then it will be logged
                if (userBookSearch.trim().length() <= 0 || userBookSearch.length() <= 0) {
                    Toast.makeText(getApplicationContext(), "No Search Entered", Toast.LENGTH_LONG).show();
                    Log.e(LOG_TAG, "Error Response code: No Search Given");
                    //if a search term is entered continue with task and log search term
                } else {
                    Log.v(LOG_TAG, userBookSearch);
                    BookAsyncTask task = new BookAsyncTask();
                    task.execute();
                }
            }
        });

        if (tempBookArrayList != null) {
            BookAdapter adapter = new BookAdapter(this, tempBookArrayList);

            bookListView.setAdapter(adapter);
        }

    }

    private void updateUi(ArrayList<Book> books) {

        tempBookArrayList = books;

        if (books != null) {
            ListView bookListView = (ListView) findViewById(R.id.list);

            BookAdapter adapter = new BookAdapter(this, books);

            bookListView.setAdapter(adapter);
        } else {
            Log.e(LOG_TAG, "Still suffering from random void errors and no results with a correct string");
        }
    }

    private class BookAsyncTask extends AsyncTask<URL, Void, ArrayList<Book>> {

        @Override
        protected ArrayList<Book> doInBackground(URL... urls) {

            URL url = null;


            url = createUrl(userBookSearch.trim());

            // Perform an HTTP request to the URL --> Receive JSON response back.
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
                // TODO Handle the IOException
            }

            ArrayList<Book> books = extractBookFromJson(jsonResponse);

            userBookSearch = "";

            return books;
        }

        @Override
        protected void onPostExecute(ArrayList<Book> books) {
            if (books == null) {
                return;
            }

            updateUi(books);
        }


        private URL createUrl(String searchTerm) {

            String baseUrl = "https://www.googleapis.com/books/v1/volumes?q=";
            String completeUrl = baseUrl + searchTerm.replace(" ", "20%") + API_KEY;
            URL url = null;
            try {
                url = new URL(completeUrl);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Error Creating URL", e);
            }

            return url;
        }

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
                    Log.e(LOG_TAG, "Response Code: " + urlConnection.getResponseCode());
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e(LOG_TAG, "Error Response Code: " + urlConnection.getResponseCode()
                            + " " + url.toString());
                }

            } catch (IOException e) {
                if (urlConnection.getResponseCode() != 200) {
                    Log.e(LOG_TAG, "Error Retrieving Earthquake JSON results", e);
                }
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            }

            return jsonResponse;
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    // Append the first line read by reader and put into StringBuilder output.
                    output.append(line);
                    // Get the next line read by reader and put into the string.
                    line = reader.readLine();
                }
            }
            return output.toString();
        }


        private ArrayList<Book> extractBookFromJson(String bookJSON) {

            if (TextUtils.isEmpty(bookJSON)) {
                return null;
            }

            ArrayList<Book> books = new ArrayList<>();


            try {
                JSONObject baseJsonResponse = new JSONObject(bookJSON);
                JSONArray bookArray = baseJsonResponse.getJSONArray("items");
                int length = bookArray.length();
                for (int i = 0; i < length; i++) {

                    /**
                     * Temporary variables for storing/augmenting data to push to a book object
                     */
                    String author = "Author: ";
                    String category = "";
                    String publisher = "Publisher: ";
                    double rating;

                    JSONObject bookObject = bookArray.getJSONObject(i);
                    JSONObject bookInfo = bookObject.getJSONObject("volumeInfo");
                    JSONObject bookPictures = bookInfo.getJSONObject("imageLinks");

                    String picture = bookPictures.getString("thumbnail");


                    String title = bookInfo.getString("title");
                    publisher += bookInfo.getString("publisher");
                    if (bookInfo.isNull("averageRating")) {
                        // Default unrated value? Hmm...
                        rating = 5;
                    } else {
                        rating = bookInfo.getDouble("averageRating");
                    }

                    JSONArray authors = bookInfo.getJSONArray("authors");

                    /**
                     * Loop functions for the author(s) array
                     */
                    if (authors.length() > 0) {
                        for (int j = 0; j < authors.length(); j++) {
                            author += authors.optString(j) + " ";
                        }
                    }

                    /**
                     * Loop functions for the category(ies) array
                     */
                    JSONArray categories = bookInfo.getJSONArray("categories");

                    if (categories.length() > 0) {
                        for (int j = 0; j < categories.length(); j++) {
                            category += categories.optString(j) + " ";
                        }
                    }

                    Log.v(LOG_TAG, title + " " + author + " " + publisher + " " + rating + " " +
                            category + " " + picture);
                    books.add(new Book(rating, title, author, publisher, category, picture));

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return books;
        }

    }

}
