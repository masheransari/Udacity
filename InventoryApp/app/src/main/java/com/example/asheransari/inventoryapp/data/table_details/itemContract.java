package com.example.asheransari.inventoryapp.data.table_details;

import android.provider.BaseColumns;

/**
 * Created by asher.ansari on 11/26/2016.
 */
public class itemContract implements BaseColumns
{
    private itemContract(){}
    public final static String TABLE_NAME_ITEM = "item_details";

    public final static String _ID = BaseColumns._ID;

    public final static String COLUMN_ITEM_DETAIL_NAME = "item_name";


}

