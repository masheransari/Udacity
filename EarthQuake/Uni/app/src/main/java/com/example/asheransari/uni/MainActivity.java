package com.example.asheransari.uni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Customclass> arrayList = Queryutil.extractEarthquakes();

        ListView listView = (ListView) findViewById(R.id.list);

        CustomAdapter customAdapter = new CustomAdapter(this,arrayList);

        listView.setAdapter(customAdapter);
    }
}
