package com.example.asheransari.petlesson3.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Loader;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.net.URL;

/**
 * Created by asher.ansari on 11/3/2016.
 */
public class PetProvider extends ContentProvider {
    public static final int PETS = 100;

    public static final int PETS_ID = 101;

    private static final UriMatcher sUrimatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUrimatcher.addURI(PetContract.CONTENT_AUTHORITY,PetContract.PATH_PETS,PETS);

        sUrimatcher.addURI(PetContract.CONTENT_AUTHORITY,PetContract.PATH_PETS+ "/#",PETS_ID);
    }

    private PetDbHelper mPetDbHelper;

    @Override
    public boolean onCreate()
    {

        mPetDbHelper = new PetDbHelper(getContext());
        return true;

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase db = mPetDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUrimatcher.match(uri);
        switch (match)
        {
            case PETS:
                cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null,null, sortOrder);
                break;
            case PETS_ID:
                selection = PetContract.PetEntry._ID = "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = db.query(PetContract.PetEntry.TABLE_NAME, projection, selection, selectionArgs, null,null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot QUery Unknown Uri"+uri);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUrimatcher.match(uri);
        switch (match){
            case PETS:
                return PetContract.PetEntry.CONTENT_LIST_TYPE;
            case PETS_ID:
                return PetContract.PetEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);

        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        final int match = sUrimatcher.match(uri);
        switch (match){
            case PETS:
                return insertPet(uri,contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
//        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionargs) {
//        return 0;
        final int match = sUrimatcher.match(uri);

        SQLiteDatabase database = mPetDbHelper.getWritableDatabase();
        switch (match){
            case PETS:
                return database.delete(PetContract.PetEntry.TABLE_NAME,selection,selectionargs);
            case PETS_ID:
                selection = PetContract.PetEntry._ID + "=?";
                selectionargs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return database.delete(PetContract.PetEntry.TABLE_NAME,selection,selectionargs);
            default:
                throw  new IllegalArgumentException("data is't delete" + uri);
        }

    }


    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionargs) {

        final int match = sUrimatcher.match(uri);
        switch (match){
            case PETS:
                return updatePet(uri,contentValues,selection,selectionargs);
            case PETS_ID:
                selection = PetContract.PetEntry._ID + "=?";
                selectionargs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updatePet(uri,contentValues,selection,selectionargs);
            default:
                throw  new IllegalArgumentException("update is not for " + uri);
    }}


    private Uri insertPet(Uri uri,ContentValues contentValues){

        SQLiteDatabase db = mPetDbHelper.getWritableDatabase();

        long id = db.insert(PetContract.PetEntry.TABLE_NAME,null,contentValues);

        if(id == -1){
            Toast.makeText(getContext(),"is not save please try again",Toast.LENGTH_SHORT).show();
            Log.e("asher","failed"+uri);
            return null;
        }

        return ContentUris.withAppendedId(uri,id);

    }

    private int updatePet(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        if (values.containsKey(PetContract.PetEntry.COLUMN_PET_NAME)){

            String name = values.getAsString(PetContract.PetEntry.COLUMN_PET_NAME);

            if (name == null){
                throw new IllegalArgumentException("pet need name");
            }
        }

        if (values.containsKey(PetContract.PetEntry.COLUMN_PET_GENDER)){

            Integer gender = values.getAsInteger(PetContract.PetEntry.COLUMN_PET_GENDER);

            if (gender == null || !PetContract.PetEntry.isValidGender(gender)){
                throw new IllegalArgumentException("pet need gender");
            }
        }

        if(values.containsKey(PetContract.PetEntry.COLUMN_PET_WEIGHT)){

            Integer weight = values.getAsInteger(PetContract.PetEntry.COLUMN_PET_WEIGHT);

            if (weight != null && weight < 0){
                throw new IllegalArgumentException("pet need weight");
            }

        }

        if (values.size() == 0){
            return 0;
        }
        SQLiteDatabase db = mPetDbHelper.getWritableDatabase();

        return db.update(PetContract.PetEntry.TABLE_NAME,values,selection,selectionArgs);
    }





}
