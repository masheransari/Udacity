/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.

        final ArrayList<variablesClass> earthquakes = QueryUtils.extractEarthquakes();
        //previous proceduress...
//       final ArrayList<variablesClass> earthquakes = new ArrayList<variablesClass>();
        ///new procedures...

//        earthquakes.add(new variablesClass(7.2,"San Francisco","Feb 20, 2016"));
//        earthquakes.add(new variablesClass(6.1,"London","Mar 15, 2015"));
//        earthquakes.add(new variablesClass(3.9,"Tokyo","Sep 20, 2014"));
//        earthquakes.add(new variablesClass(5.4,"Mexico City","Aug 5, 2014"));
//        earthquakes.add(new variablesClass(2.8,"Moscow","Jan 14, 2013"));
//        earthquakes.add(new variablesClass(4.9,"Rio de Janeiro","Nov 28, 2012"));
//        earthquakes.add(new variablesClass(1.6,"Paris","Oct 30, 2011"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
//        ArrayAdapter<variablesClass> adapter = new ArrayAdapter<variablesClass>(
//                this, R.layout.listdetails, earthquakes);

        adapter Ad = new adapter(this,earthquakes);
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(Ad);
    }
}
