package com.mlgrier.okctourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set the content of the activity to use the activity_main. xml layout file
        setContentView(R.layout.activity_main);

        //Find the view that shows the Events/Casinos/Family/Nature category
        TextView events = (TextView) findViewById(R.id.eventsBtn);
        //Set a click listener on that view
        events.setOnClickListener(new View.OnClickListener() {
            //The code is this method will be executed when the events view is clicked on
            @Override
            public void onClick(View v) {
                //Create a new intent to open the {@link Events Activity
                Intent eventsIntent = new Intent(MainActivity.this, EventsActivity.class);
                //Start the new activity
                startActivity(eventsIntent);

            }
        });

        TextView casinos = (TextView) findViewById(R.id.casinosBtn);
        casinos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent casinosIntent = new Intent(MainActivity.this, CasinosActivity.class);
                startActivity(casinosIntent);
            }
        });

        TextView family = (TextView) findViewById(R.id.familyBtn);
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        TextView nature = (TextView) findViewById(R.id.natureBtn);
        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent natureIntent = new Intent(MainActivity.this, NatureActivity.class);
                startActivity(natureIntent);
            }
        });
    }


}
