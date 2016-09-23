package com.example.asheransari.uni;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by asher.ansari on 9/23/2016.
 */
public class CustomAdapter extends ArrayAdapter<Customclass>  {

CustomAdapter(Activity activity,ArrayList<Customclass> arrayAdapter){
    super(activity,0,arrayAdapter);
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listitemview = convertView;

        if(listitemview == null){
            listitemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Customclass currentlist = getItem(position);

        TextView magnitude = (TextView) listitemview.findViewById(R.id.magnitudeview);

        magnitude.setText(""+currentlist.getMagnitude());

        TextView place = (TextView) listitemview.findViewById(R.id.place);

        place.setText(""+currentlist.getPlace());

//        long timesInMillisSecond = currentlist.getTime();
//        Date dateObject = new Date(timesInMillisSecond);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM DD, YYYY");
//        String dateToDisplay = dateFormat.format(dateObject);
//        date.setText(dateToDisplay);
        TextView date = (TextView) listitemview.findViewById(R.id.date);
        date.setText(""+currentlist.getTime());
        return listitemview;
    }
}

