package com.example.asheransari.petlesson3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.petlesson3.data.PetContract;
import com.example.asheransari.petlesson3.data.PetDbHelper;

import java.io.IOException;

public class Catalog extends AppCompatActivity {

    private PetDbHelper mPetDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton fb = (FloatingActionButton)findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Catalog.this, editor.class);
                startActivity(i);
            }
        });

    mPetDbHelper = new PetDbHelper(this);


    }

    @Override
    protected void onStart(){
        super.onStart();
        displayDatabaseInfo();
    }
    private void displayDatabaseInfo()
    {
        String[] projection= {
                PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_PET_NAME,
                PetContract.PetEntry.COLUMN_PET_BREED,
                PetContract.PetEntry.COLUMN_PET_GENDER,
                PetContract.PetEntry.COLUMN_PET_WEIGHT
        };

        TextView textView = (TextView)findViewById(R.id.text_view_pet);
        Cursor cursor = getContentResolver().query(PetContract.PetEntry.CONTENT_URI,projection, null,null,null);

        try
        {
            textView.setText("The pets Table Contains "+cursor.getCount());
            textView.append(PetContract.PetEntry._ID + " - " +
                    PetContract.PetEntry.COLUMN_PET_NAME + " - " +
                    PetContract.PetEntry.COLUMN_PET_BREED + " - " +
                    PetContract.PetEntry.COLUMN_PET_GENDER + " - " +
                    PetContract.PetEntry.COLUMN_PET_WEIGHT + "\n");

            int idColumnIndex = cursor.getColumnIndex(PetContract.PetEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
            int breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
            int genderColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
            int weightColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);

            while (cursor.moveToNext())
            {
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentWeight = cursor.getInt(weightColumnIndex);

                textView.append(("\n"+currentId+" - "+
                currentName + " - "+
                currentBreed+ " - "+
                currentGender+ " - "+
                currentWeight));
            }
        }
        catch (RuntimeException e)
        {
            Log.e("Catalog","Exception Found: "+e.getMessage());
        }
        finally {
            cursor.close();
        }

    }

    private void insertPet()
    {
        SQLiteDatabase db = mPetDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PetContract.PetEntry.COLUMN_PET_NAME,"Toto");
        values.put(PetContract.PetEntry.COLUMN_PET_BREED,"Terrier");
        values.put(PetContract.PetEntry.COLUMN_PET_GENDER,"1");
        values.put(PetContract.PetEntry.COLUMN_PET_WEIGHT,"1");
        long newRowId = db.insert(PetContract.PetEntry.TABLE_NAME,null,values);
//        String name = values.getAsString(PetContract.PetEntry.COLUMN_PET_NAME);
//        Toast.makeText(this,"insertpet="+name,Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    //    Sirf ap ki yeh line menus ko set krne ki line khatm ho rhai hai,,,..
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            case R.id.action_delete_all_entries:
                
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
