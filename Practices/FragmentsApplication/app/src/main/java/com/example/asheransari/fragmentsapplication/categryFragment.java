package com.example.asheransari.fragmentsapplication;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Toast;
/**
 * Created by asher.ansari on 9/24/2016.
 */

public class categryFragment extends FragmentPagerAdapter {
    private Context context;
    public categryFragment(Context conetxt, FragmentManager fm){
        super(fm);
        this.context = conetxt;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if(position == 0){
            return new first();

        }
        else if(position == 1){
            return new second();        }
        else {
            return new third();        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "FIRST";
        }
        else if(position == 1){
            return "SECOND";
        }
        else {
            return "THIRD";
        }
    }
}
