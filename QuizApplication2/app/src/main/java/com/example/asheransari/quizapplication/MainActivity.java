package com.example.asheransari.quizapplication;

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

    Button btn, b1;

    int n=0;
    private RadioGroup radioGroup;
    private RadioButton radioButtonSelected;
    public int count=1;
    int index=0;
    private int number=0;
    public int status=0;
    final ArrayList<Integer> arrList= new ArrayList<Integer>();
    final ArrayList<String> questions = new ArrayList<String>();
    final Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.start);
        btn = (Button)findViewById(R.id.next);
        b1.setVisibility(View.VISIBLE);
        btn.setVisibility(View.INVISIBLE);
//        final int sum=0;
//        int [] resul = {0,0,0,0,0,0,0,0,0,0};

/////this is start button

        for (int i=0; i<10;i++)
        {
            arrList.add(i);
        }

//        int [] arr = {1,2,3,4,5,6,7,8,9,10};
        questions.add("What is an activity in Android?_Activity performs the actions on the screen_Screen UI_Manage the Application content_1");//1st
        questions.add("How many sizes are supported by Android?_Android supported all sizes_Android supports small,normal, large and extra-large sizes_Size is undefined in android_3");//3rd wala
        questions.add("How to access the context in android content provider?_Using getContext() in onCreate()_Using getApplicationContext() at anywhere in an application_Both A & B_3");//3rd wala
//        questions.add("What is APK in android?_Android packaging kit_Android package_None of the above._1");//1st wala ok hai
        questions.add("The best practice to give \"textSize\" in :_pt_sp_px_2");//2nd wala
        questions.add("Images are drop in which folder?_Drawable_Values_Mipmap_1");//1st wala
        questions.add("How many Orientation in RelativeLayout?_Vertical_Horizontal_None of them_3");//3rd wala
        questions.add("Which of these are not one of the two main components of the APK?_Dalvik Execuyable_Webkit_Resources_2");//2nd wala
        questions.add("What is contained within the Manifest xml file?_The permission the app requires_The list of Strings used in the app_All the above_1");//1st wala
        questions.add("The R file is a(an) generated file?_Manually_Emulated_Automatically_3");//3rd wala

        ////this is a start button.................................
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this," index = "+index,Toast.LENGTH_SHORT).show();

                index=0;
                index = rand.nextInt(arrList.size());

                String [] quesQuiz = questions.get(index).split("_");

                TextView t1 = (TextView)findViewById(R.id.question);
                t1.setText(quesQuiz[0]);
                RadioButton r1 =(RadioButton)findViewById(R.id.rad1);
                RadioButton r2 =(RadioButton)findViewById(R.id.rad2);
                RadioButton r3 =(RadioButton)findViewById(R.id.rad3);
                r1.setText(quesQuiz[1]);
                r2.setText(quesQuiz[2]);
                r3.setText(quesQuiz[3]);
//                Toast.makeText(MainActivity.this," index = "+index,Toast.LENGTH_SHORT).show();
                arrList.remove(index);
                btn.setVisibility(View.VISIBLE);
                b1.setVisibility(View.INVISIBLE);
            }
        });



//////this is a next button

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String data=null;
//////////////////////////*****************************yeha se compiler check kre ga ke jo user ne select ki hai kia wo wakai sahe hai ya nhe...
//                if (count < 10) {
//
//                    radioGroup = (RadioGroup) findViewById(R.id.radioGp);
//                    int idDaata = radioGroup.getCheckedRadioButtonId();
//                    radioButtonSelected = (RadioButton) findViewById(idDaata);
//                    data = radioButtonSelected.getText().toString();
//                    if (!data.equals("")){
//                    String[] arr = questions.get(index).split("_");
//                    int option = 0;
//                    option = Integer.valueOf(arr[4]);
//
//                    if (data.equals(arr[option])) {
//                        getNumber(10);
//                    }
//                    else
//                    {
//                        getNumber(0);
//                    }
//                    Toast.makeText(MainActivity.this, ", Count = "+count,Toast.LENGTH_SHORT).show();
//
//
                questions.remove(index);
//
///////////////////////////////****************************yeha se compiler new question ko generte krke set kre ga...
//                    ++count;
                    display();
//                    }
//                    else
//                    {
//                        Toast.makeText(MainActivity.this,"Please Select First",Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else
//                {
//                    Toast.makeText(MainActivity.this,setNumber(),Toast.LENGTH_SHORT).show();
//                    RadioButton r1 =(RadioButton)findViewById(R.id.rad1);
//                    RadioButton r2 =(RadioButton)findViewById(R.id.rad2);
//                    RadioButton r3 =(RadioButton)findViewById(R.id.rad3);
//                    r1.setChecked(false);
//                    r2.setChecked(false);
//                    r3.setChecked(false);
//                    r1.setText("");
//                    r2.setText("");
//                    r3.setText("");
//                    Toast.makeText(MainActivity.this, "Sorry No More Question Available!!\nPlease Press The Result Button",Toast.LENGTH_SHORT).show();
//                }
            }
            });


        Button btnResult = (Button)findViewById(R.id.result);
        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
int resultMain=0;
                if (count==10)
                {

                    for (int i=0;i<10;i++)
                    {
//                        resultMain = resultMain + resul[i];
                    }
                    Toast.makeText(MainActivity.this,resultMain, Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Please Complete The Test First!!",Toast.LENGTH_SHORT).show();
                }

            }
        });





    }

    public int setNumber()
    {
        return number;
    }
    public void getNumber(int num)
    {
        this.number = num+10;
    }

public void display()
{
    index=0;
    index = rand.nextInt(arrList.size());
//                    System.out.println("Selected: "+list.remove(index));
    arrList.remove(index);
//                radioButtonSelected.setSelected(false);
    String [] quesQuiz = questions.get(index).split("_");
    TextView t1 = (TextView)findViewById(R.id.question);
    t1.setText("");
    t1.setText(quesQuiz[0]);

    RadioButton r1 =(RadioButton)findViewById(R.id.rad1);
    RadioButton r2 =(RadioButton)findViewById(R.id.rad2);
    RadioButton r3 =(RadioButton)findViewById(R.id.rad3);
    r1.setChecked(false);
    r2.setChecked(false);
    r3.setChecked(false);
    r1.setText("");
    r1.setText(quesQuiz[1]);
    r2.setText("");
    r2.setText(quesQuiz[2]);
    r3.setText("");
    r3.setText(quesQuiz[3]);
//    Toast.makeText(MainActivity.this," number = "+number,Toast.LENGTH_SHORT).show();

}


}





