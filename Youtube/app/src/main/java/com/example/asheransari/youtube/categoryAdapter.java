package com.example.asheransari.youtube;

import android.content.Context;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.ImageView;

/**
 * Created by asher.ansari on 11/17/2016.
 */
public class categoryAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public categoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0)
        {
            return new videoClass();
        }
        else if(i==1)
        {
            return new userClass();
        }
        else
        {
            return new userClass();
        }

//        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
//        if (position == 0)
//        {
//            return mContext.getString(R.string.home);
//        }
//        else if (position == 1)
//        {
//            return mContext.getString(R.string.subscriber);
//        }
//        else
//        {
//            return mContext.getString(R.string.user);
//        }
        return null;
    }
}
