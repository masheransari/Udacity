package com.developer.jc.booklisting;

import android.content.Context;
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
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class fetches books from the google books API
 */
public class FetchBooks extends AsyncTask<Void, Void, Void> {
    BufferedReader reader;
    HttpURLConnection urlConnection;
    JSONArray results;
    JSONObject json;
    Book[] mBooks;
    private Context mContext;
    private String searchString;
    SearchFragment mSearchFragment;

    public FetchBooks(Context context, String search, SearchFragment searchFragment) {
        this.mContext = context;
        this.searchString = search;
        this.mSearchFragment = searchFragment;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mSearchFragment.updateAdapter(mBooks);
    }

    @Override
    protected Void doInBackground(Void... params) {

        String apiKey = "AIzaSyAypNzmAwKSJ07kvZxnP6WVffn1Fg91w6g";

            try {
                final String FETCH_BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes";

                Uri builtUri = Uri.parse(FETCH_BOOK_BASE_URL).buildUpon()
                        .appendQueryParameter("q", searchString)
                        .build();

                URL url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //Read input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                if (inputStream == null) {
                    Log.d("", "input stream is null");
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                String s = buffer.toString();
                if (s != null) {
                    //if String is not null, parse the JSON
                    json = new JSONObject(s);
                    parseJsonBooks(json);
                }
            } catch (MalformedURLException e) {
                //Empty text view will be displayed in place of ListView
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                //Empty text view will be displayed in place of ListView
                Log.e("", "Error", e);
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                //Empty text view will be displayed in place of ListView
                e.printStackTrace();
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        Log.e("", "error closing stream", e);
                    }
                }

            }
        return null;
    }

    /**
     * Parse the results of the JSON object to retrieve Books
     * @param json json object returned by API call
     * @throws JSONException
     */
    private void parseJsonBooks(JSONObject json) throws JSONException{
        results = json.getJSONArray("items");
        mBooks = new Book[results.length()];
        for(int i = 0; i < results.length(); i++) {
            JSONObject volume = results.getJSONObject(i);
            JSONObject volumeInfo = volume.getJSONObject("volumeInfo");
            Book book = new Book();

            book.setTitle(volumeInfo.getString("title"));
            try {
                JSONArray temp = volumeInfo.getJSONArray("authors");
                String[] authors = new String[temp.length()];
                for(int j = 0; j < authors.length; j++) {
                    authors[j] = temp.getString(j);
                }
                book.setAuthors(authors);
            } catch(JSONException e) {
                Log.d("", "Null Book");
            }
            mBooks[i] = book;
        }
    }

    public interface SearchUpdate {
        public void updateAdapter(Book[] books);
    }
}
