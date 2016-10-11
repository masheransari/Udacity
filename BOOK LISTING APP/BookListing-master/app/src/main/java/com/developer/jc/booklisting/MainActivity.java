package com.developer.jc.booklisting;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements SearchFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null) {
            MainActivityFragment mainActivityFragment = new MainActivityFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment, mainActivityFragment, "")
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //This method must be implemented for proper fragment transitions
    }
}
