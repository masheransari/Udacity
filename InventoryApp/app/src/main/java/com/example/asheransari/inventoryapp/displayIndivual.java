package com.example.asheransari.inventoryapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asheransari.inventoryapp.R;

import org.w3c.dom.Text;

public class displayIndivual extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_indivual);

        Intent i = getIntent();
        String PName = i.getStringExtra("PName");
        String MName = i.getStringExtra("MName");
        String MCost = i.getStringExtra("PCost");
        String MStock = i.getStringExtra("PStock");

//        TextView t = (TextView)findViewById(R.id.text_Temp);
//        String temp = "Name = "+PName+", MName = "+MName+" , PCost = "+MCost+" , MStock = "+MStock;
//        t.setText(temp);
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
                Toast.makeText(displayIndivual.this,"Save Button Selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete_display:
                Toast.makeText(displayIndivual.this,"Delete Is Selected",Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
