package com.example.asheransari.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        int tempNumber=0;

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        Intent recieve = getIntent();
        tempNumber = recieve.getIntExtra("number",0);
        String TempStatus = status(tempNumber);
        TextView t1 = (TextView)findViewById(R.id.textStatus);
        if (tempNumber>=40)
        {
            imageView.setImageResource(R.drawable.congratulations);
            t1.setText("You Have Passed..\n"+TempStatus);
        }
        else
        {
            imageView.setImageResource(R.drawable.fail);
            t1.setText(TempStatus);
        }

    }


    public String status(int limit)
    {
        String data=null;
        if (limit==100)
        {
            data= "Excellent Job";
        }
        else if (limit==90)
        {
            data= "Excellent Job";
        }
        else if (limit==80)
        {
            data= "Well Done Job";
        }
        else if (limit==70)
        {
            data= "Good Job";
        }
        else if (limit==60)
        {
            data= "Good Job";
        }
        else if (limit==50)
        {
            data= "Fair Job";
        }
        else if (limit==40)
        {
            data= "Promoted\nBut Try To Give Our Best In Future";
        }
        else if (limit==30)
        {
            data= "Soory!! You Are Failed..!!";
        }
        else if (limit==20)
        {
            data= "Try To Working Hard In Android..!!\nYou Are Fail...";
        }
        else if (limit==10 || limit==0)
        {
            data= "No Argsuments..!!\nYou Are Fail...";
        }
        else
        {
            data = "";

        }
        return data;
    }
}
