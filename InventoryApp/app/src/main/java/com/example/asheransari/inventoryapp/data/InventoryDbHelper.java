package com.example.asheransari.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asheransari.inventoryapp.data.table_details.inventoryContract;
import com.example.asheransari.inventoryapp.data.table_details.itemContract;

/**
 * Created by asher.ansari on 11/21/2016.
 */
public class InventoryDbHelper extends SQLiteOpenHelper {

    Context mContext;
    private static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_DETAILS_TABLE = "CREATE TABLE "+ inventoryContract.TABLE_NAME + " ("
            + inventoryContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + inventoryContract.COLUMN_DETAILS_PRODUCT_NAME + " TEXT NOT NULL, "
            + inventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE + " TEXT NOT NULL, "
            + inventoryContract.COLUMN_DETAILS_QUANTITY + " INTEGER NOT NULL, "
            + inventoryContract.COLUMN_DETAILS_RS + " INTEGER NOT NULL DEFAULT 0 "+");";
    private static final String SQL_CREATE_ITEM_NAME_TABLE = "CREATE TABLE "+ itemContract.TABLE_NAME_ITEM + " ("
            + itemContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + itemContract.COLUMN_ITEM_DETAIL_NAME +" TEXT NOT NULL );";

    public InventoryDbHelper(Context context)
    {
        super(context ,DATABASE_NAME ,null ,DATABASE_VERSION );

//        Toast.makeText(context, "In inventoryDbHelper Class",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_DETAILS_TABLE);
//        Toast.makeText(null, "Database Created", Toast.LENGTH_SHORT).show();
//        onCreate(sqLiteDatabase);
        sqLiteDatabase.execSQL(SQL_CREATE_ITEM_NAME_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+inventoryContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
