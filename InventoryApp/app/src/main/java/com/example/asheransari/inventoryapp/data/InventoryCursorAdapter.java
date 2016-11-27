package com.example.asheransari.inventoryapp.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.asheransari.inventoryapp.R;
import com.example.asheransari.inventoryapp.data.table_details.inventoryContract;


/**
 * Created by asher.ansari on 11/21/2016.
 */
public class InventoryCursorAdapter extends CursorAdapter {
    public InventoryCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    //
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {


        TextView name = (TextView)view.findViewById(R.id.item_name);
        TextView Mname = (TextView)view.findViewById(R.id.item_manufacture);

        TextView quantity = (TextView)view.findViewById(R.id.item_quantity);
        TextView price = (TextView)view.findViewById(R.id.item_price);
//
        int nameColumnIndex = cursor.getColumnIndex(inventoryContract.COLUMN_DETAILS_PRODUCT_NAME);
        int manufactureColumnNameIndex = cursor.getColumnIndex(inventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE);
        int quantityColumnIndex = cursor.getColumnIndex(inventoryContract.COLUMN_DETAILS_QUANTITY);
        int RsColumnIndex = cursor.getColumnIndex(inventoryContract.COLUMN_DETAILS_RS);

        String PName = cursor.getString(nameColumnIndex);
        String manufactureName = cursor.getColumnName(manufactureColumnNameIndex);
        String P_quantity = cursor.getColumnName(quantityColumnIndex);
        String p_rs = cursor.getColumnName(RsColumnIndex);
//
////        if ()
//        //        if (TextUtils.isEmpty())
        name.setText(PName);
        Mname.setText(manufactureName);
        quantity.setText(P_quantity);
        price.setText(p_rs);
//
////Es Tarha ka yeha pe kuch kam hoga......
//
//
////        // Find the columns of pet attributes that we're interested in
//        int nameColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
//        int breedColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
////
////        // Read the pet attributes from the Cursor for the current pet
//        String petName = cursor.getString(nameColumnIndex);
//        String petBreed = cursor.getString(breedColumnIndex);
////
////        // If the pet breed is empty string or null, then use some default text
////        // that says "Unknown breed", so the TextView isn't blank.
////        if (TextUtils.isEmpty(petBreed)) {
////            petBreed = context.getString(R.string.unknown_breed);
////        }
////
////        // Update the TextViews with the attributes for the current pet
////        nameTextView.setText(petName);
////        summaryTextView.setText(petBreed);
//
    }
}
