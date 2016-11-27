package com.example.asheransari.inventoryapp.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.asheransari.inventoryapp.R;
import com.example.asheransari.inventoryapp.variable_classes.variableItemClass;

import java.util.ArrayList;

/**
 * Created by asher.ansari on 11/26/2016.
 */
public class inventoryItemAdapter extends ArrayAdapter<variableItemClass> {

    public inventoryItemAdapter(Activity activity, ArrayList<variableItemClass> arrayList)
    {
        super(activity,0,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if (view == null)
        {
            view = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item_detail,parent,false);
        }

        variableItemClass variableItemClass = getItem(position);

        TextView textView = (TextView)view.findViewById(R.id.item_name_id);

        textView.setText(variableItemClass.getmItem());

        return view;
    }

}
