package com.example.asheransari.petudacity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.asheransari.petudacity.data.PetDbHelper;
import com.example.asheransari.petudacity.data.PetContract.PetEntry;

public class category extends AppCompatActivity {
    private PetDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(category.this, Editor.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        displayDatabaseInfo();
    }
    private void displayDatabaseInfo()
    {
        String[] projections = {
                PetEntry.COLUMN_PET_NAME,
                PetEntry.COLUMN_PET_BREED,
                PetEntry.COLUMN_PET_GENDER,
                PetEntry.COLUMN_PET_WEIGHT
        };

        Cursor cursor = getContentResolver().query(
                PetEntry.CONTENT_URL,projections, null,null,null);
        TextView displayView = (TextView)findViewById(R.id.text_view_pet);

        try
        {
            displayView.setText("The Pet Table Contains + "+cursor.getCount()+" pets.\n\n");
            displayView.append(PetEntry._ID + " - "+PetEntry.COLUMN_PET_NAME + " - "+ PetEntry.COLUMN_PET_BREED + " - "+PetEntry.COLUMN_PET_GENDER+ " - " + PetEntry.COLUMN_PET_WEIGHT+ "\n");

            int idColumnIndex = cursor.getColumnIndex(PetEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_NAME);
            int breedColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_BREED);
            int genderColumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);
            int weightClumnIndex = cursor.getColumnIndex(PetEntry.COLUMN_PET_GENDER);

            while (cursor.moveToNext())
            {

                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentBreed = cursor.getString(breedColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                int currentWeight = cursor.getInt(weightClumnIndex);
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentBreed + " - " +
                        currentGender + " - " +
                        currentWeight));
            }

        }
        finally {
            cursor.close();
        }
    }


    //    Sirf ap ki yeh line menuss ko set kr rhe hai..
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    //    Sirf ap ki yeh line menus ko set krne ki line khatm ho rhai hai,,,..
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert_dummy_data:
                insertPet();
                displayDatabaseInfo();
                return true;
            case R.id.action_delete_all_enteries:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void insertPet()
    {
        ContentValues values = new ContentValues();
        values.put(PetEntry.COLUMN_PET_NAME, "Toto");
        values.put(PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetEntry.COLUMN_PET_GENDER, "2");
        values.put(PetEntry.COLUMN_PET_WEIGHT, "10");
//        Uri uri = getContentResolver().insert(PetEntry.CONTENT_URL,values);
    }
}