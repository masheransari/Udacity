package com.udacity.udacitybooklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ModelAdapter extends ArrayAdapter<Model> {

    public ModelAdapter(Context context, List<Model> models) {
        super(context, 0, models);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Model model = getItem(position);

        TextView titleTextView = (TextView) listItemView.findViewById(R.id.list_item_title);
        TextView authorTextView = (TextView) listItemView.findViewById(R.id.list_item_author);

        titleTextView.setText("Title: " + model.getTitle());
        authorTextView.setText("Author: " + model.getAuthors());

        return listItemView;
    }
}
