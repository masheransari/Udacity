package com.example.asheransari.inventoryapp.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;


/**
 * Created by asher.ansari on 11/21/2016.
 */
public class InventoryCursorAdapter extends CursorAdapter{
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
//        return LayoutInflater.from(context).inflate(R.layout.list_item,viewGroup,false);
        return null;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


    }
}
