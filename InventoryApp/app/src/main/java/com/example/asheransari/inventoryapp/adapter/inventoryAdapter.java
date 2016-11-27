package com.example.asheransari.inventoryapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asheransari.inventoryapp.R;
import com.example.asheransari.inventoryapp.variable_classes.variableClass;

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
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }

            variableClass  variableClass = getItem(position);
            TextView name = (TextView)view.findViewById(R.id.item_name);
            TextView Mname = (TextView)view.findViewById(R.id.item_manufacture);

            TextView quantity = (TextView)view.findViewById(R.id.item_quantity);
            TextView price = (TextView)view.findViewById(R.id.item_price);

            name.setText(variableClass.getPName());
            Mname.setText("Manufacture Name : "+variableClass.getmName());
            quantity.setText(String.valueOf(variableClass.getMquantity())+" Pieces available");
            price.setText("Rs : "+String.valueOf(variableClass.getMcost())+" per piece");


        return view;
    }
}
