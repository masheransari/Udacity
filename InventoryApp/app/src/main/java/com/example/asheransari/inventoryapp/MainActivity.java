package com.example.asheransari.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.asheransari.inventoryapp.adapter.inventoryAdapter;
import com.example.asheransari.inventoryapp.data.InventoryCursorAdapter;
import com.example.asheransari.inventoryapp.data.InventoryDbHelper;
import com.example.asheransari.inventoryapp.data.table_details.inventoryContract;
import com.example.asheransari.inventoryapp.variable_classes.variableClass;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private InventoryDbHelper mInventoryDbHelper;
    InventoryCursorAdapter mCursorAdapter;
    inventoryContract mInventoryContract;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInventoryDbHelper = new InventoryDbHelper(this);
//        SQLiteDatabase db = mInventoryDbHelper.getWritableDatabase();
        listView = (ListView)findViewById(R.id.list);
        View emptyVew = findViewById(R.id.empty_view);
        listView.setEmptyView(emptyVew);

        final ArrayList<variableClass> arrayList = displayDatabaseInfo();
        inventoryAdapter inventoryAdapter = new inventoryAdapter(MainActivity.this, arrayList);

        listView.setAdapter(inventoryAdapter);
//        displayDatabaseInfo();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Toast.makeText(MainActivity.this,"Position = "+position,Toast.LENGTH_SHORT).show();

//                variableClass  variableClass =
                variableClass variableClass = arrayList.get(position);

                Intent i = new Intent(MainActivity.this,displayIndivual.class);
                i.putExtra("PName",variableClass.getPName());
                i.putExtra("MName",variableClass.getmName());
                i.putExtra("PCost",String.valueOf(variableClass.getMcost()));
                i.putExtra("PStock",String.valueOf(variableClass.getMquantity()));
                startActivity(i);
//                Toast.makeText(MainActivity.this,""+variableClass.getPName()+" , "+variableClass.getmName()+" , "+ variableClass.getMquantity()+" , "+variableClass.getMcost(),Toast.LENGTH_SHORT).show();
            }
        });



//                this is also the part of onCreateContextMenu
//                registerForContextMenu(listView);
    }
//    /here the onCreateContextMenu Detail.
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info)
//    {
//        super.onCreateContextMenu(menu, v, info);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.context_menu,menu);
//    }
//
//    public boolean onContextItemSelected(MenuItem item)
//    {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        switch (item.getItemId())
//        {
//            case R.id.delete:
//                Toast.makeText(MainActivity.this,"Delete is Selecte",Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(MainActivity.this,insert.class);
//                startActivity(i);
//                return true;
//            case R.id.update:
//                Toast.makeText(MainActivity.this,"Update is Selecte",Toast.LENGTH_SHORT).show();
//                Intent j = new Intent(MainActivity.this,displayIndivual.class);
//                startActivity(j);
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
//
//      }

    private void insertDb()
    {
        SQLiteDatabase db = mInventoryDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(inventoryContract.COLUMN_DETAILS_PRODUCT_NAME, "HandFree");
        values.put(inventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE, "Samsung");
        values.put(inventoryContract.COLUMN_DETAILS_QUANTITY, 50);
        values.put(inventoryContract.COLUMN_DETAILS_RS, 120);

        db.insert(inventoryContract.TABLE_NAME, null, values);
    }
    private ArrayList<variableClass> displayDatabaseInfo()
    {
        ArrayList<variableClass> arrayList = new ArrayList<variableClass>();
//        InventoryDbHelper inventoryDbHelper = new InventoryDbHelper(this);
        SQLiteDatabase db = mInventoryDbHelper.getReadableDatabase();

//        Cursor cursor = db.rawQuery("SELECT "+mInventoryContract.COLUMN_DETAILS_PRODUCT_NAME +","+mInventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE+ ","+mInventoryContract.COLUMN_DETAILS_STOCK+","+mInventoryContract.COLUMN_DETAILS_RS +" FROM "+mInventoryContract.TABLE_NAME, null);

        String[] projection = {
                mInventoryContract.COLUMN_DETAILS_PRODUCT_NAME,
                mInventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE,
                mInventoryContract.COLUMN_DETAILS_QUANTITY,
                mInventoryContract.COLUMN_DETAILS_RS
        };
        Cursor cursor = db.query(
            mInventoryContract.TABLE_NAME,
                projection, null,null,null,null,null
        );

//        Toast.makeText(MainActivity.this, ""+cursor.getCount(), Toast.LENGTH_SHORT).show();
        try
        {
            int pNameColumn = cursor.getColumnIndex(inventoryContract.COLUMN_DETAILS_PRODUCT_NAME);
            int mNameColumn = cursor.getColumnIndex(inventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE);
            int mQuantity = cursor.getColumnIndex(inventoryContract.COLUMN_DETAILS_QUANTITY);
            int mRs = cursor.getColumnIndex(inventoryContract.COLUMN_DETAILS_RS);

            while(cursor.moveToNext())
            {
                String name = cursor.getString(pNameColumn);
                String Mname = cursor.getString(mNameColumn);
//                Toast.makeText(MainActivity.this,"Here 1",Toast.LENGTH_SHORT).show();
                int quantity = cursor.getInt(mQuantity);
                int mRupees = cursor.getInt(mRs);
//                Toast.makeText(MainActivity.this,"Here 2",Toast.LENGTH_SHORT).show();

                arrayList.add(new variableClass(name, Mname,quantity,mRupees));
//                Log.e("MainActivity","name = "+name + ", Manufacture Name = "+Mname+ " , Quantity = "+quantity+ " , Rupees = "+mRs);
            }

//            inventoryAdapter inventoryAdapter = new inventoryAdapter(MainActivity.this, arrayList);
//
//            listView.setAdapter(inventoryAdapter);
        }
        finally {
                cursor.close();
        }
        return arrayList;
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
//                insertDb();
//                displayDatabaseInfo();
                Intent i = new Intent(MainActivity.this, insert.class);
                startActivity(i);
                return true;
            case R.id.insert_item_only:
                Intent ia = new Intent(MainActivity.this, itemNew.class);
                startActivity(ia);
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }
    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}
