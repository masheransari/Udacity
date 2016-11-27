package com.example.asheransari.inventoryapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.inventoryapp.R;
import com.example.asheransari.inventoryapp.*;
import com.example.asheransari.inventoryapp.data.table_details.inventoryContract;
import com.example.asheransari.inventoryapp.data.InventoryDbHelper;

public class displayIndivual extends AppCompatActivity {

    TextView pNameTxt_data, mNameTxt_data, quantityTxt,quantityMain;
    EditText total_rs_data,quatity_editText;
    Button btnPlus, btnMinus;
    private InventoryDbHelper mInventoryDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_indivual);
        mInventoryDbHelper = new InventoryDbHelper(this);

        Intent i = getIntent();
        String PName = i.getStringExtra("PName");
        String MName = i.getStringExtra("MName");
        String MCost = i.getStringExtra("PCost");
        final String MStock = i.getStringExtra("PStock");

        btnMinus = (Button)findViewById(R.id.btn_negitive);
        btnPlus = (Button)findViewById(R.id.btn_plus);

        total_rs_data = (EditText)findViewById(R.id.rs_display_data);
        total_rs_data.setHint("Current Rupees = "+MCost);

        quantityMain = (TextView)findViewById(R.id.quantity_main);

        quantityMain.setText("Current Quantity = "+MStock);
        pNameTxt_data = (TextView)findViewById(R.id.pName_display_data);
        pNameTxt_data.setText(PName);

        mNameTxt_data = (TextView)findViewById(R.id.mName_display_data);
        mNameTxt_data.setText(""+MName);

        quantityTxt = (TextView)findViewById(R.id.quantity_display_data);
        quantityTxt.setText(""+MStock);


        quatity_editText = (EditText)findViewById(R.id.quantity_user_edit);
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              int temp = Integer.valueOf(quatity_editText.getText().toString());
                temp = Integer.valueOf(MStock)+temp;
                quantityTxt.setText(String.valueOf(temp));
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int temp = Integer.valueOf(quatity_editText.getText().toString());
                temp = Integer.valueOf(MStock)- temp;
                quantityTxt.setText(String.valueOf(temp));
            }
        });
    }

public void savedDataOnDatabase()
{
    String pName = pNameTxt_data.getText().toString();
    String mName = mNameTxt_data.getText().toString();
    String pquantity = quantityTxt.getText().toString();
    String pRs = total_rs_data.getText().toString();

    SQLiteDatabase db = mInventoryDbHelper.getWritableDatabase();

    if (pName.equals("")||pName.equals(" ") || mName.equals(" ")||mName.equals("")|| pquantity.equals("")||pquantity.equals(" ")||pRs.equals("")|| pRs.equals(" ")) {
        Toast.makeText(displayIndivual.this, "Please Fill All The Information Correctly..!!", Toast.LENGTH_SHORT).show();
    }
    else {
        ContentValues values = new ContentValues();
        values.put(inventoryContract.COLUMN_DETAILS_RS, Integer.valueOf(pRs));
        values.put(inventoryContract.COLUMN_DETAILS_QUANTITY, Integer.valueOf(pquantity));
//    db.update(inventoryContract.TABLE_NAME,values,inventoryContract.COLUMN_DETAILS_PRODUCT_NAME+"="+pName+ " AND "+inventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE +" = "+mName,null);
//    db.rawQuery("UPDATE "+inventoryContract.TABLE_NAME + " SET "+inventoryContract.COLUMN_DETAILS_QUANTITY+"="+pquantity+" , "+inventoryContract.COLUMN_DETAILS_RS + "="+pRs + " WHERE "+inventoryContract.COLUMN_DETAILS_PRODUCT_NAME+" = '"+pName+"'"+" AND "+inventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE +"='"+mName+"'",null);
        db.update(inventoryContract.TABLE_NAME, values, inventoryContract.COLUMN_DETAILS_PRODUCT_NAME + "=? AND " + inventoryContract.COLUMN_DETAILS_PRODUCT_MANUFACTURE + " = ?", new String[]{pName, mName});
        clearAll();
    }
}

    private void clearAll()
    {
        pNameTxt_data.setText("");
        mNameTxt_data.setText("");
        quantityMain.setText("");
        quatity_editText.setText("");
        quantityTxt.setText("");
        total_rs_data.setText("");
        total_rs_data.setHint("");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menuItem)
    {
        getMenuInflater().inflate(R.menu.activity_display_menus,menuItem);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case R.id.save_display:
//                Toast.makeText(displayIndivual.this,"Save Button Selected",Toast.LENGTH_SHORT).show();
                savedDataOnDatabase();
                return true;
            case R.id.delete_display:
//                Toast.makeText(displayIndivual.this,"Delete Is Selected",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
