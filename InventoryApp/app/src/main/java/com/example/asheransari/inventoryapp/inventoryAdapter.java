package com.example.asheransari.inventoryapp;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by asher.ansari on 11/25/2016.
 */
public class inventoryAdapter extends ArrayAdapter<variableClass>{

    public inventoryAdapter(Activity activity, ArrayList<variableClass> arrayList)
    {
        super(activity,0,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null){
            variableClass  variableClass = getItem(position);
            TextView name = (TextView)view.findViewById(R.id.item_name);
            TextView Mname = (TextView)view.findViewById(R.id.item_manufacture);

            TextView quantity = (TextView)view.findViewById(R.id.item_quantity);
            TextView price = (TextView)view.findViewById(R.id.item_price);

            name.setText(variableClass.getpName());
            Mname.setText(variableClass.getmName());
            quantity.setText(variableClass.getMquantity());
            price.setText(variableClass.getMcost());

        }
        return view;
    }
}
