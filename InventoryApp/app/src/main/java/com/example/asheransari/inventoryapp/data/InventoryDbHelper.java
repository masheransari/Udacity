package com.example.asheransari.inventoryapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by asher.ansari on 11/21/2016.
 */
public class InventoryDbHelper extends SQLiteOpenHelper {

    Context mContext;
    private static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DATABASE_VERSION = 1;

    public InventoryDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_DETAILS_TABLE = "CREATE TABLE IF NOT EXISTS "+ inventoryContract.TABLE_NAME + " ("
                + inventoryContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + inventoryContract.COLUMN_DETAILS_PRODUCT_NAME + " TEXT NOT NULL, "
                + inventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE + " TEXT NOT NULL, "
                + inventoryContract.COLUMN_DETAILS_QUANTITY + " INTEGER NOT NULL, "
                + inventoryContract.COLUMN_DETAILS_STOCK + " INTEGER NOT NULL, "
                + inventoryContract.COLUMN_DETAILS_RS + " INTEGER NOT NULL DEFAULT 0);";
        sqLiteDatabase.execSQL(SQL_CREATE_DETAILS_TABLE);
        Toast.makeText(null, "Database Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}