package com.example.android.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by benwong on 2016-07-09.
 */
public class LocationImageAdapter extends ArrayAdapter<LocationImage> {
    public LocationImageAdapter(Context context, ArrayList<LocationImage> locationImages) {
        super(context, 0, locationImages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        LocationImage locationImage = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_locationimage, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvLocation = (TextView) convertView.findViewById(R.id.tvLocation);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.listImage);
        // Populate the data into the template view using the data object
        tvName.setText(locationImage.name);
        tvLocation.setText(locationImage.location);
        imageView.setImageResource(locationImage.image);
        // Return the completed view to render on screen
        return convertView;
    }
}