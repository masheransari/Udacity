package com.example.asheransari.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.inventoryapp.R;
import com.example.asheransari.inventoryapp.adapter.inventoryItemAdapter;
import com.example.asheransari.inventoryapp.data.InventoryDbHelper;
import com.example.asheransari.inventoryapp.data.table_details.itemContract;
import com.example.asheransari.inventoryapp.variable_classes.variableItemClass;
import com.example.asheransari.inventoryapp.data.table_details.*;
import java.util.ArrayList;

public class insert extends AppCompatActivity {

    Spinner spinner;
    InventoryDbHelper mInventoryDbHelper;
    public String spinner_text = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mInventoryDbHelper = new InventoryDbHelper(this);
        spinner = (Spinner)findViewById(R.id.spinner_Item);
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList = fetchData();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(arrayAdapter);




        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(insert.this, "data = "+spinner.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
              spinner_text = spinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
//                spinner_text = get
                spinner_text = spinner.getItemAtPosition(0).toString();
            }
        });

        Button btn = (Button)findViewById(R.id.btn_insert);
        final EditText mName = (EditText)findViewById(R.id.manufacture_name);
        final EditText stock = (EditText)findViewById(R.id.edit_stock);
        final EditText rs1 = (EditText)findViewById(R.id.edit_price);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String manufacture = mName.getText().toString();
            String StockQuantity = stock.getText().toString();
            String rupees = rs1.getText().toString();

            if (manufacture.equals("") || StockQuantity.equals(" ")||rupees.equals("") || manufacture.equals(" ") || StockQuantity.equals(" ")||rupees.equals(" "))
            {
                Toast.makeText(insert.this,"Please FIll THe information Correctily",Toast.LENGTH_SHORT);            }
            else
            {
//                Toast.makeText(insert.this,"Please FIll THe information Correctily",Toast.LENGTH_SHORT);

                SQLiteDatabase db = mInventoryDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put(inventoryContract.COLUMN_DETAILS_PRODUCT_NAME,spinner_text);
                values.put(inventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE,manufacture);
                values.put(inventoryContract.COLUMN_DETAILS_QUANTITY,StockQuantity);
                values.put(inventoryContract.COLUMN_DETAILS_RS,rupees);
                db.insert(inventoryContract.TABLE_NAME,null,values);
                mName.setText("");
                stock.setText("");
                rs1.setText("");
                spinner.setSelection(0);
                Toast.makeText(insert.this,"Successfully Inserted on Record.",Toast.LENGTH_SHORT).show();

            }

            }
        });


    }

    public ArrayList fetchData()
    {
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = mInventoryDbHelper.getReadableDatabase();

        String[] projection = {
                itemContract.COLUMN_ITEM_DETAIL_NAME
        };

        Cursor cursor = db.query(itemContract.TABLE_NAME_ITEM, projection,null,null,null,null,null,null);

            int itemIndex = cursor.getColumnIndex(itemContract.COLUMN_ITEM_DETAIL_NAME);
            while(cursor.moveToNext())
            {
                String itemName = cursor.getString(itemIndex);
                arrayList.add(itemName);
            }


        return arrayList;
    }
}
