package com.example.asheransari.viewpagerapplication;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Monday extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contianer, Bundle saveInstanceState)
    {
        return inflater.inflate(R.layout.activity_monday,contianer,false);
    }

//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_monday);
//
//    }
}
