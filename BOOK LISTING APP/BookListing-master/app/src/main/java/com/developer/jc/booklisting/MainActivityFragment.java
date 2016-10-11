package com.developer.jc.booklisting;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends android.support.v4.app.Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        final EditText editText = (EditText) view.findViewById(R.id.searchCriteria);
        //Get String to search from text field
        Button searchButton = (Button) view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mSearch = editText.getText().toString();

                if(isNetworkAvailable(getContext())) {
                    SearchFragment searchFragment = new SearchFragment();
                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment, searchFragment)
                            .addToBackStack(null)
                            .commit();

                    FetchBooks fetchBooks = new FetchBooks(getContext(), mSearch, searchFragment);
                    fetchBooks.execute();
                } else {
                    Toast.makeText(getContext(), "Sorry there is no connection available.", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        return view;
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE));
        //Return whether or nt connected to internet
        return connectivityManager.getActiveNetworkInfo() != null
                && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
