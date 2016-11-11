package com.example.asheransari.petudacity;

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
                        mGender = 1;
                    }
                    else if(selection.equals("Female"))
                    {
                        mGender = 2;
                    }
                    else
                    {
                        mGender = 0;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mGender=0;
            }
        });


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
                return true;
            case R.id.action_delete:
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



}
