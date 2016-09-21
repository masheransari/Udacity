package com.example.asheransari.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnNext, btnResult;
    public RadioGroup radioGroup;
    public RadioButton radioButtonSelected;
    int index=0;
    public int number=0;
    public ArrayList<Integer> arrList= new ArrayList<Integer>();
    public String [] arr = new String[5];
    public ArrayList<String> questions = new ArrayList<String>();
    int increment = 0;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNext = (Button)findViewById(R.id.next);
        btnNext.setText("Goto Question 1");
        btnResult = (Button)findViewById(R.id.result);
        btnResult.setVisibility(View.INVISIBLE);

        for (int i=0; i<10;i++)
        {
            arrList.add(i);
        }
        questions.add("What is an activity in Android?_Activity performs the actions on the screen_Screen UI_Manage the Application content_1");//1st
        questions.add("How many sizes are supported by Android?_Android supported all sizes_Android supports small,normal, large and extra-large sizes_Size is undefined in android_3");//3rd wala
        questions.add("How to access the context in android content provider?_Using getContext() in onCreate()_Using getApplicationContext() at anywhere in an application_Both A & B_3");//3rd wala
        questions.add("What is APK in android?_Android packaging kit_Android package_None of the above._1");//1st wala ok hai
        questions.add("The best practice to give \"textSize\" in :_pt_sp_px_2");//2nd wala
        questions.add("Images are drop in which folder?_Drawable_Values_Mipmap_1");//1st wala
        questions.add("How many Orientation in RelativeLayout?_Vertical_Horizontal_None of them_3");//3rd wala
        questions.add("Which of these are not one of the two main components of the APK?_Dalvik Execuyable_Webkit_Resources_2");//2nd wala
        questions.add("What is contained within the Manifest xml file?_The permission the app requires_The list of Strings used in the app_All the above_1");//1st wala
        questions.add("The R file is a(an) generated file?_Manually_Emulated_Automatically_3");//3rd wala
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = null;
                increment = increment+1;
                RadioButton ra1 = (RadioButton)findViewById(R.id.rad1);
                RadioButton ra2 = (RadioButton)findViewById(R.id.rad2);
                RadioButton ra3 = (RadioButton)findViewById(R.id.rad3);
                if (increment <= 11) {
                    if(increment ==10)
                    {

                        display();
                        btnNext.setText("Click Here To Save Question 10 Answer");
                    }
                    else if (increment == 11)
                    {
                        btnResult.setVisibility(View.VISIBLE);
                        btnNext.setVisibility(View.GONE);
                        btnNext.setText("Sorry No Question Available");
                        ra1.setText("");
                        ra1.setVisibility(View.INVISIBLE);
                        ra2.setText("");
                        ra2.setVisibility(View.INVISIBLE);
                        ra3.setText("");
                        ra3.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        if(increment == 1)
                        {
                            btnNext.setText("Goto Question " + (increment + 1));
                            display();
                        }
                        else {
                            int check = 0;
                            data = null;
                            check = checkSelect();
                            if (check == 1)
                            {
                                data = ra1.getText().toString();
                            }
                            else if (check == 2)
                            {
                                data = ra2.getText().toString();
                            }
                            else if (check == 3)
                            {
                                data = ra3.getText().toString();
                            }
                            else
                            {
                                data = "a";
                            }
                            int option = 0;
                            option = Integer.valueOf(arr[4]);
                            if (data.equals(arr[option])) {
                            number = number + 10;
                            }
                            else
                            {
                            number += 0;
                            }
                            btnNext.setText("Goto Question " + (increment + 1));
                            display();
                        }
                    }
                }
                else{
                    Toast.makeText(MainActivity.this, "Sorry No Question Available!!\nPlease Press The Result Button",Toast.LENGTH_SHORT).show();
                }
            }
        });

/////////yeha tak ke hamra kam sirf question ke button pe hwa hai...
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,result.class);
                i.putExtra("number",number);
                startActivity(i);
            }
        });

    }

    public int checkSelect()
    {
        int returnInt = 0;
        RadioButton ra1 = (RadioButton)findViewById(R.id.rad1);
        RadioButton ra2 = (RadioButton)findViewById(R.id.rad2);
        RadioButton ra3 = (RadioButton)findViewById(R.id.rad3);

        if(ra1.isChecked())
        {
            returnInt = 1;
        }
        else if(ra2.isChecked())
        {
            returnInt = 2;
        }
        else if(ra3.isChecked())
        {
            returnInt = 3;
        }
        else
        {
            returnInt = 4;
        }

        return returnInt;
    }

    public void display()
    {
        index = 0;
        index = rand.nextInt(arrList.size());
        String[] quesQuiz = questions.get(index).split("_");
        for (int i=0;i <5;i++)
        {
            arr[i] = quesQuiz[i];
        }
        arrList.remove(index);
        questions.remove(index);
        TextView t1 = (TextView) findViewById(R.id.question);
        t1.setText("");
        t1.setText(quesQuiz[0]);
        RadioButton r1 = (RadioButton) findViewById(R.id.rad1);
        RadioButton r2 = (RadioButton) findViewById(R.id.rad2);
        RadioButton r3 = (RadioButton) findViewById(R.id.rad3);
        r1.setChecked(false);
        r2.setChecked(false);
        r3.setChecked(false);
        r1.setText("");
        r1.setText(quesQuiz[1]);
        r2.setText("");
        r2.setText(quesQuiz[2]);
        r3.setText("");
        r3.setText(quesQuiz[3]);
    }
}