package com.example.asheransari.miwokpractices;

import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

//    TextView t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getActionBar().setDisplayHomeAsUpEnabled(true);


        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
CatagoryAdapter adapter = new CatagoryAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupwithViewPager(viewPager);

//        t1 = (TextView)findViewById(R.id.number);
//        t2 = (TextView)findViewById(R.id.family);
//        t3 = (TextView)findViewById(R.id.color);
//        t4 = (TextView)findViewById(R.id.phrase);
//        t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,number_activity.class);
//                startActivity(i);
//            }
//        });
//        t2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,family_activity.class);
//                startActivity(i);
//            }
//        });
//        t3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,color_activity.class);
//                startActivity(i);
//            }
//        });
//        t4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this,phrase_activity.class);
//                startActivity(i);
//            }
//        });


    }
}
