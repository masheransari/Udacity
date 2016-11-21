package com.example.asheransari.inventoryapp;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asheransari.inventoryapp.data.InventoryDbHelper;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private InventoryDbHelper mInventoryDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInventoryDbHelper = new InventoryDbHelper(this);
//        mInventoryDbHelper.
        ListView listView = (ListView)findViewById(R.id.list);

        View emptyVew = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyVew);


        Toast.makeText(MainActivity.this,"Null",Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main_activity_insert, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case R.id.insert_button:
                Intent i = new Intent(MainActivity.this, insert.class);
                startActivity(i);
                return true;
            case R.id.insert_item_only:
                Intent ia = new Intent(MainActivity.this, itemNew.class);
                startActivity(ia);
                return true;
            case R.id.Display_Item:
                Intent ib = new Intent(MainActivity.this, displayIndivual.class);
                startActivity(ib);
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
