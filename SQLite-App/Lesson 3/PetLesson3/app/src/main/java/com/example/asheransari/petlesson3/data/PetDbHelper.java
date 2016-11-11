package com.example.asheransari.petlesson3.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by asher.ansari on 11/3/2016.
 */
public class PetDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "shelter.db";
    public static final int DATABASE_VERSION = 1;

    public PetDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        SQLiteDatabase

        String SQL_CREATE_PET_TABLE = "CREATE TABLE IF NOT EXISTS "+ PetContract.PetEntry.TABLE_NAME+
                " ("+ PetContract.PetEntry._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetContract.PetEntry.COLUMN_PET_NAME+" TEXT NOT NULL, "
                + PetContract.PetEntry.COLUMN_PET_BREED+" TEXT, "
                + PetContract.PetEntry.COLUMN_PET_GENDER+ " INTEGER NOT NULL, "
                + PetContract.PetEntry.COLUMN_PET_WEIGHT+ " INTEGER NOT NULL DEFAULT 0)";
        sqLiteDatabase.execSQL(SQL_CREATE_PET_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
