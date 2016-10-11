package com.example.asheransari.viewpagerapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by asher.ansari on 9/29/2016.
 */
public class pagerClass extends FragmentPagerAdapter {
    public pagerClass(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position==0)
        {
            return new Monday();
        }
        else if (position==1)
        {
            return new Tuesday();
        }
        else if (position==2)
        {
            return new Wednesday();
        }
        else if (position==3)
        {
            return new Thursday();
        }
        else if (position==4)
        {
            return new Friday();
        }
        else
        {
            return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
