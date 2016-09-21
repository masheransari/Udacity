package com.example.android.quakereport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asher.ansari on 9/21/2016.
 */
public class adapter extends ArrayAdapter<variablesClass> {
    public adapter(Context context, ArrayList<variablesClass> arrayList) {
        super(context, 0 ,arrayList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listdetails, parent, false);
        }

        variablesClass variablesClass = getItem(position);
        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);

        magnitude.setText(""+variablesClass.getMagnitude());

        TextView place = (TextView) listItemView.findViewById(R.id.place);
        place.setText(""+variablesClass.getPlace());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(""+variablesClass.getDate());

        return listItemView;
    }
}
