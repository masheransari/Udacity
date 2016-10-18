package com.example.asheransari.businesscard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name, email, number, designation;
    private static final String LOG_TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.name);
        designation = (EditText)findViewById(R.id.designation);
        email = (EditText)findViewById(R.id.email);
        number = (EditText)findViewById(R.id.number);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SubmitData();
            }
        });
    }

    private void SubmitData()
    {

        String [] data = new String[4];
        data[0] = name.getText().toString();
        data[1] = designation.getText().toString();
        data[2] = email.getText().toString();
        data[3] = number.getText().toString();
        boolean check = false;

        check = isValidEmail(data[2]);

        if (check == false)
        {
            data[2]="";
        }
        Log.e("MainActivity", "check = "+check + " and data[2] = "+data[2]);
        if (!data[0].equals("") && !data[1].equals("") && !data[3].equals("")  )
        {

            if (!data[2].equals("")){
            Intent intent = new Intent(MainActivity.this, Card.class);
            intent.putExtra("name",data[0]);
            intent.putExtra("designation",data[1]);
            data[2] = "Email: "+data[2];
            intent.putExtra("email",data[2]);
            data[3] = "Cell: "+data[3];
            intent.putExtra("number",data[3]);
            name.setText("");
            designation.setText("");
            email.setText("");
            number.setText("");
            startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Please Enter A Valid Email",Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), getString(R.string.ToastMsgElse), Toast.LENGTH_LONG).show();
        }
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
