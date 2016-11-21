package com.example.asheransari.youtube;

import android.content.res.Resources;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        categoryAdapter adapter = new categoryAdapter(this,getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);

//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.home_icon));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.subscribe));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.user));

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home_icon);
//        tabLayout.getTabAt(0).setText(R.string.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.subscribe);
//        tabLayout.getTabAt(0).setText(R.string.subscriber);
        tabLayout.getTabAt(2).setIcon(R.drawable.user);

    }

    }

