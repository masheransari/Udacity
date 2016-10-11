package com.example.asheransari.fragmentsapplication;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by asher.ansari on 9/24/2016.
 */

public class first extends android.support.v4.app.Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.listview, container, false);
       /* TextView textView = (TextView) rootview.findViewById(R.id.a);
        textView.setText("heee");
       */
        return rootview;

    }
}