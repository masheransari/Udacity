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
package com.example.android.tourguide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


public class ShoppingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_shopping, container, false);


        // Construct the data source
        ArrayList<Location> arrayOfLocations = new ArrayList<Location>();
// Create the adapter to convert the array to views
        LocationAdapter adapter = new LocationAdapter(getActivity(), arrayOfLocations);
// Attach the adapter to a ListView
        ListView listView = (ListView) v.findViewById(R.id.shoppingListView);

        listView.setAdapter(adapter);

        Location newLocation1 = new Location(getActivity().getString(R.string.marketMallName), getActivity().getString(R.string.marketMallAddress));
        Location newLocation2 = new Location(getActivity().getString(R.string.marlMallName), getActivity().getString(R.string.marlMallAddress));
        Location newLocation3 = new Location(getActivity().getString(R.string.southCentreMallName), getActivity().getString(R.string.southCentreMallAddress));
        Location newLocation4 = new Location(getActivity().getString(R.string.chinookName), getActivity().getString(R.string.chinookAddress));

        adapter.add(newLocation1);
        adapter.add(newLocation2);
        adapter.add(newLocation3);
        adapter.add(newLocation4);

        return v;
    }
}
