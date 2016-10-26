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

/**
 * Fragment that displays "Tuesday".
 */
public class ArtsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_arts, container, false);

        ArrayList<Location> arrayOfLocations = new ArrayList<Location>();
        LocationAdapter adapter = new LocationAdapter(getActivity(), arrayOfLocations);
        ListView listView = (ListView) v.findViewById(R.id.artsListView);

        listView.setAdapter(adapter);

        Location newLocation1 = new Location(getActivity().getString(R.string.glenbowname), getActivity().getString(R.string.glenbowaddress));
        Location newLocation2 = new Location(getActivity().getString(R.string.aerospacename), getActivity().getString(R.string.aerospaceaddress));
        Location newLocation3 = new Location(getActivity().getString(R.string.telusname), getActivity().getString(R.string.telusaddress));
        Location newLocation4 = new Location(getActivity().getString(R.string.hallname), getActivity().getString(R.string.halladdress));

        adapter.add(newLocation1);
        adapter.add(newLocation2);
        adapter.add(newLocation3);
        adapter.add(newLocation4);

        return v;
    }
}
