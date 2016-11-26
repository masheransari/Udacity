package com.example.asheransari.inventoryapp.data;

import android.provider.BaseColumns;

/**
 * Created by asher.ansari on 11/21/2016.
 */
public class inventoryContract implements BaseColumns {
    private inventoryContract(){}

    public final static String TABLE_NAME = "details";

    public final static String _ID = BaseColumns._ID;

    public final static String COLUMN_DETAILS_PRODUCT_NAME = "Pname";

    public final static String COLUMN_DETAILS_PRODUCT_MANUFACTURE = "Mname";

    public final static String COLUMN_DETAILS_QUANTITY = "quantity";

    public final static String COLUMN_DETAILS_RS = "rs";

//    private inventoryContract(String name, String mName, int quantity, int price)
//    {
//        this.N
//    }
}
