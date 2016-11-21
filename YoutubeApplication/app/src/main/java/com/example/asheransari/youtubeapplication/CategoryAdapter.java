package com.example.asheransari.youtubeapplication;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by asher.ansari on 11/17/2016.
 */
public class CategoryAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public CategoryAdapter(Context context, FragmentManager fm)
    {
        super(fm);
        mContext = context;
    }
    @Override
    public Fragment getItem(int i) {
        if (i==0)
        {
            return new home();
        }
        else if (i==1)
        {
            return new subscribe();
        }
        else
        {
            return new user();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        if (position == 0)
        {
            return mContext.getText(R.string.home);
        }
        else if (position == 1)
        {
            return mContext.getText(R.string.subscribe);
        }
        else
        {
            return mContext.getText(R.string.user);
        }
    }
}
