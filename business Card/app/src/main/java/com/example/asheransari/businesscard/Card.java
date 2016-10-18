package com.example.asheransari.businesscard;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Card extends AppCompatActivity {
    private TextView name, desig, num, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        int orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        // or = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        setRequestedOrientation(orientation);


        String [] data = new String[4];
        ////yeha pe jaha pe set krwane hai uska bre me trha krlena...

        Intent i = getIntent();
        data[0] = i.getStringExtra("name");
        data[1] = i.getStringExtra("designation");
        data[2] = i.getStringExtra("number");
        data[3] = i.getStringExtra("email");

        name = (TextView)findViewById(R.id.fetchName);
        name.setText("");
        name.setText(data[0]);

        desig = (TextView)findViewById(R.id.fetchDesignation);
        desig.setText("");
        desig.setText(data[1]);

        num = (TextView)findViewById(R.id.cellNumber);
        num.setText("");
        num.setText(data[2]);

        email = (TextView)findViewById(R.id.fetchEmail);
        email.setText("");
        email.setText(data[3]);
//        id = cellNumbwer;
//        id = fetchName;
//        id = fetchDesignation;
//        id = fetchEmail;

        ////yeha pe en ko set krwa denge,,,,

    }
}
