package com.example.asheransari.youtubeapplication;

import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.choiceMenu);
//        textView.setBackgroundResource(R.drawable.dot);

//        ViewPager viewPager = (ViewPager)findViewById(R.id.viewPager);

//        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());
//
//        viewPager.setAdapter(adapter);

        YouTubeAsyncTask tubeAsyncTask = new YouTubeAsyncTask();
        tubeAsyncTask.execute();



//        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
    }

private CategoryAdapter madapter;
private ViewPager mviewPager;
    private class YouTubeAsyncTask extends AsyncTask<List<CustomClass>, Void, CategoryAdapter>
    {


        @Override
        protected CategoryAdapter doInBackground(List<CustomClass>... params) {

            mviewPager = (ViewPager)findViewById(R.id.viewPager);

            madapter = new CategoryAdapter(getBaseContext(), getSupportFragmentManager());

            return madapter;
        }

        @Override
        protected void onPostExecute(CategoryAdapter categoryAdapter) {
//            super.onPostExecute(categoryAdapter);
            mviewPager.setAdapter(madapter);
            tabLayout = (TabLayout)findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mviewPager);
        }
    }
}
