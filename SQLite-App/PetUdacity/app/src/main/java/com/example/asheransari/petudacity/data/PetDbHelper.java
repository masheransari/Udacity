package com.example.asheransari.petudacity.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.asheransari.petudacity.data.PetContract.PetEntry;
/**
 * Created by asher.ansari on 10/28/2016.
 */
public class PetDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "shelter.db";

    private static final int DATABASE_VERSION = 1;
    public PetDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//esme hum petEntry ki class ko call kren ge because usme hamara tamam tar variables define hai to bajae
// -wapas likhne ke hum is ko direct call krlen ge..
//      String SQL_CREATE_PETS_TABLE = "CREATE TABLE "+PetEntry.TABLE_NAME+ "("
//                +PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                +PetEntry.COLUMN_PET_NAME+ " TEXT NOT NULL,"
//                +PetEntry.COLUMN_PET_BREED + " TEXT,"
//                +PetEntry.COLUMN_PET_GENDER + "INTEGER NOT NULL,"
//                +PetEntry.COLUMN_PET_WEIGHT + "INTEGER NOT NULL DEFAULT 0);";

        String SQL_CREATE_PETS_TABLE =  "CREATE TABLE IF NOT EXISTS" + PetEntry.TABLE_NAME + " ("
                + PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                + PetEntry.COLUMN_PET_BREED + " TEXT, "
                + PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
                + PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";

        db.execSQL(SQL_CREATE_PETS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
