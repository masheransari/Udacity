package com.example.android.booklisting;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class RestTask extends AsyncTask<Uri, Void, String>
{
    private OnRestTaskCompleted mListener;

    public RestTask(OnRestTaskCompleted aListener)
    {
        mListener = aListener;
    }

    @Override
    protected String doInBackground(Uri... params)
    {
        try
        {
            URL myURL = new URL(params[0].toString());
            HttpURLConnection c = (HttpURLConnection) myURL.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Accept", "application/json");

            int responseCode = c.getResponseCode();

            if (responseCode != 200)
            {
                throw new RuntimeException("Failed : HTTP error code : "
                        + c.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((c.getInputStream())));

            String result = "";
            String chunk;
            while ((chunk = br.readLine()) != null)
            {
                result += chunk;
            }

            return result;
        }
        catch (Exception e)
        {
            // TODO handle this properly
            e.printStackTrace();
            return "";
        }
    }

    @Override
    protected void onPostExecute(String aResult)
    {
        mListener.onRestTaskCompleted(aResult);
    }

}