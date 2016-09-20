package com.example.asheransari.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);



        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.congratulations);
    }


    public String status(int limit)
    {
        String data=null;
        if (limit==100)
        {
            data= "Excellent Job";
        }
        if (limit==90)
        {
            data= "Excellent Job";
        }
        if (limit==80)
        {
            data= "Well done Job";
        }
        if (limit==70)
        {
            data= "Good Job";
        }
        if (limit==60)
        {
            data= "Good Job";
        }
        if (limit==50)
        {
            data= "Fair Job";
        }
        if (limit==40)
        {
            data= "Satisfactory Job";
        }
        if (limit==30)
        {
            data= "Soory!! you are failed..!!";
        }
        if (limit==20)
        {
            data= "Try to working Hard in Android..!!";
        }
        if (limit==10)
        {
            data= "No Argsuments..!!";
        }
        else
        {
            data = "";

        }
        return data;
    }
}
