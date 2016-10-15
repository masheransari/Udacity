package com.udacity.udacitybooklistingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private EditText editTextSearch;
    private Button btnSearch;
    private TextView tvMainEmptyListView;

    private ArrayList<Model> modelArrayList;
    private ListView listView;
    private ModelAdapter adapter;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String GOOGLE_REQUEST_URL = "https://www.googleapis.com/books/v1/volumes?q=";
    private static final String maxResults = "&maxResults=10";
    private String final_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextSearch = (EditText) findViewById(R.id.et_main_search);
        btnSearch = (Button) findViewById(R.id.btn_main_search);
        listView = (ListView) findViewById(R.id.list);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager connMgr = (ConnectivityManager)
                        getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    modelArrayList.clear();
                    String searchText = editTextSearch.getText().toString();
                    final_url = GOOGLE_REQUEST_URL + searchText.replaceAll(" ", "%20") + maxResults;

                    GoogleAsyncTask task = new GoogleAsyncTask();
                    task.execute();
                    editTextSearch.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "No data connection", Toast.LENGTH_SHORT).show();
                }
            }
        });

        modelArrayList = new ArrayList<>();
        adapter = new ModelAdapter(this, modelArrayList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("listView", modelArrayList);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
        modelArrayList = savedInstanceState.getParcelableArrayList("listView");
        adapter = new ModelAdapter(this, modelArrayList);
        listView.setAdapter(adapter);
    }


    private void updateUi(List<Model> mdArrayList) {
        adapter.addAll(mdArrayList);
        adapter.notifyDataSetChanged();
    }

    private class GoogleAsyncTask extends AsyncTask<URL, Void, ArrayList<Model>> {

        @Override
        protected ArrayList<Model> doInBackground(URL... urls) {
            // Create URL object
            URL url = createUrl(final_url);

            // Perform HTTP request to the URL and receive a JSON response back
            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e) {
            }
            return extractFeatureFromJson(jsonResponse);

        }

        @Override
        protected void onPostExecute(ArrayList<Model> modelList) {
            if (modelList == null) {
                tvMainEmptyListView= (TextView) findViewById(R.id.tv_main_emptyListView);
                listView.setEmptyView(tvMainEmptyListView);
                return;
            }
            {
                updateUi(modelList);
            }
        }

        private URL createUrl(String stringUrl) {
            URL url = null;
            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                Log.e(LOG_TAG, "Error with creating URL", e);
                return null;
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
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e("MainActivity", "Error response code: " + urlConnection.getResponseCode());
                }

            } catch (IOException e) {

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

        private ArrayList<Model> extractFeatureFromJson(String googleJSON) {
            Model model;

            try {
                JSONObject baseJsonResponse = new JSONObject(googleJSON);
                JSONArray itemsArray = baseJsonResponse.optJSONArray("items");

                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject firstObject = itemsArray.getJSONObject(i);
                    JSONObject volumeInfoObject = firstObject.getJSONObject("volumeInfo");

                    String title = volumeInfoObject.getString("title");
                    String authors;

                    if (volumeInfoObject.has("authors")) {
                        authors = volumeInfoObject.getString("authors");
                        authors = authors.replace("[", "");
                        authors = authors.replace("]", "");
                        authors = authors.replace("\"", "");

                        model = new Model(authors, title);
                        modelArrayList.add(model);
                    } else {
                        model = new Model("No authors", title);
                        Log.i("Model", model.toString());
                        modelArrayList.add(model);
                    }
                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return modelArrayList;
        }
    }


}

