package com.example.asheransari.petudacity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.asheransari.petudacity.data.PetContract;
import com.example.asheransari.petudacity.data.PetDbHelper;

public class Editor extends AppCompatActivity {

    private EditText mNameText;
    private EditText mBreedText;
    private EditText mWeightText;
    private Spinner mGenderSpinner;

    private int mGender = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        mNameText = (EditText)findViewById(R.id.edit_pet_name);
        mBreedText = (EditText)findViewById(R.id.edit_per_breed);
        mWeightText = (EditText)findViewById(R.id.edit_pet_weight);
        mGenderSpinner = (Spinner)findViewById(R.id.spinner_gender);

        setupSpinner();
    }

    private void setupSpinner()
    {
        String [] data = {"Unknown","Male","Female"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item,data);
//        ArrayAdapter getSpinnerAdapter = ArrayAdapter.createFromResource(this,R.array.array_gender_options,android.R.layout.simple_dropdown_item_1line);
        mGenderSpinner.setAdapter(arrayAdapter);

        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);

                if (!TextUtils.isEmpty(selection))
                {
                    if (selection.equals("Male"))
                    {
                        mGender = PetContract.PetEntry.GENDER_MALE;
                    }
                    else if(selection.equals("Female"))
                    {
                        mGender = PetContract.PetEntry.GENDER_FEMALE;
                    }
                    else
                    {
                        mGender = PetContract.PetEntry.GENDER_UNKNOWN;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mGender = PetContract.PetEntry.GENDER_UNKNOWN;
            }
        });
    }
    private void insertPet()
    {
        String nameString = mNameText .getText().toString().trim();
        String breedString = mBreedText.getText().toString().trim();
        String weightString = mWeightText.getText().toString().trim();
        int weight = Integer.parseInt(weightString);

        PetDbHelper dbHelper = new PetDbHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PetContract.PetEntry.COLUMN_PET_NAME, nameString);
        values.put(PetContract.PetEntry.COLUMN_PET_BREED, breedString);
        values.put(PetContract.PetEntry.COLUMN_PET_GENDER, mGender);
        values.put(PetContract.PetEntry.COLUMN_PET_WEIGHT, weight);

//        long newRowId = db.insert(PetContract.PetEntry.TABLE_NAME, null, values);
        Uri newUri = getContentResolver().insert(PetContract.PetEntry.CONTENT_URL, values);
        if (newUri == null)
        {
            Toast.makeText(this, getString(R.string.editor_insert_pet_failed),Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, getString(R.string.editor_insert_pet_saved),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_save:
                insertPet();
                finish();
            case R.id.action_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
