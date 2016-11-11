package com.example.asheransari.petudacity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class category extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(category.this, Editor.class);
                startActivity(i);
            }
        });
    }
//    Sirf ap ki yeh line menuss ko set kr rhe hai..
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }
//    Sirf ap ki yeh line menus ko set krne ki line khatm ho rhai hai,,,..
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.insert_dummy_data:
//            Intent i = new Intent(category.this, Editor.class);
//                startActivity(i);
            return true;
            case R.id.action_delete_all_enteries:
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }
}
