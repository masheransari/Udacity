package com.example.asheransari.inventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.inventoryapp.R;
import com.example.asheransari.inventoryapp.data.InventoryDbHelper;
import com.example.asheransari.inventoryapp.variable_classes.variableItemClass;
import com.example.asheransari.inventoryapp.data.table_details.itemContract;
import com.example.asheransari.inventoryapp.adapter.inventoryItemAdapter;

import java.util.ArrayList;

public class itemNew extends AppCompatActivity {

    private InventoryDbHelper mInventoryDbHelper;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_new);
        mInventoryDbHelper = new InventoryDbHelper(this);

        listView = (ListView)findViewById(R.id.list_item_id);
        View emptyView = (View)findViewById(R.id.empty_view_id);

        listView.setEmptyView(emptyView);

        displayItemInfo();

        Button btn = (Button)findViewById(R.id.btn_id);
        final EditText editText = (EditText) findViewById(R.id.edit_item_id);
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = String.valueOf(editText.getText());
                if (TextUtils.isEmpty(text)|| text.equals("") || text.equals(" "))
                {
                    Toast.makeText(itemNew.this,"Please Enter a Valid Item Name",Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    Toast.makeText(itemNew.this,"in else COndition",Toast.LENGTH_SHORT).show();
                    insertDb(text);
                    displayItemInfo();
                }
//                Toast.makeText(itemNew.this,"data = "+text,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertDb(String data)
    {
        boolean result = checkData(data);
        if (result == true)
        {
            Toast.makeText(itemNew.this, "Already In Record..", Toast.LENGTH_SHORT).show();
        }
        else
        {
//            Toast.makeText(itemNew.this,"New Record",Toast.LENGTH_SHORT).show();
            SQLiteDatabase sqLiteDatabase = mInventoryDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(itemContract.COLUMN_ITEM_DETAIL_NAME,data);

            sqLiteDatabase.insert(itemContract.TABLE_NAME_ITEM, null,values);

        }
    }
    private boolean checkData(String data)
    {
        boolean check = false;
        ArrayList<String> arrayList = new ArrayList<String>();

        SQLiteDatabase db = mInventoryDbHelper.getReadableDatabase();

        String[] projection = {
                itemContract.COLUMN_ITEM_DETAIL_NAME
        };

        Cursor cursor = db.query(itemContract.TABLE_NAME_ITEM, projection,null,null,null,null,null,null);

        try
        {
            int itemIndex = cursor.getColumnIndex(itemContract.COLUMN_ITEM_DETAIL_NAME);

            while(cursor.moveToNext())
            {
                String itemName = cursor.getString(itemIndex);
                arrayList.add(itemName);
            }
        }
        finally {
            cursor.close();
        }

        for (int i=0; i<arrayList.size();i++)
        {
            if (data.equals(arrayList.get(i)))
            {
                return check = true;
            }
        }

    return check;
    }

    private void displayItemInfo()
    {
        ArrayList<variableItemClass> arrayList = new ArrayList<variableItemClass>();

        SQLiteDatabase db = mInventoryDbHelper.getReadableDatabase();

        String[] projection = {
            itemContract.COLUMN_ITEM_DETAIL_NAME
        };

        Cursor cursor = db.query(itemContract.TABLE_NAME_ITEM, projection,null,null,null,null,null,null);

        try
        {
            int itemIndex = cursor.getColumnIndex(itemContract.COLUMN_ITEM_DETAIL_NAME);
            arrayList.add(new variableItemClass("\t\tThe Following Items We Have..!!"));
            while(cursor.moveToNext())
            {
                String itemName = cursor.getString(itemIndex);
                arrayList.add(new variableItemClass(itemName));
            }
            inventoryItemAdapter adapter = new inventoryItemAdapter(itemNew.this,arrayList);

            listView.setAdapter(adapter);
            final EditText editText = (EditText) findViewById(R.id.edit_item_id);
            editText.setText("");
        }
        finally {
            cursor.close();
        }

    }

}
