package com.example.android.booklisting;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.preference.PreferenceActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import static android.R.id.message;

/**
 * Created by ABHISHEK RAJ on 9/26/2016.
 */

public class MenuActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.items, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.hello:
                Toast.makeText(getBaseContext(), "Hello User", Toast.LENGTH_SHORT).show();
                break;
            case R.id.email:
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:rajtheinnovatorabhishek@gmail.com")); // only email apps should handle this
                intent.putExtra(Intent.EXTRA_TEXT, message);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                break;
            //Make the Up button click take the activity to the expected behavior(here move back to main activity)
            case android.R.id.home:
                /**
                 * Don't prefer it, but may be useful in some cases
                 * MenuActivity.this.onBackPressed();
                 **/
                NavUtils.navigateUpFromSameTask(this); //This is preferable
                break;
        }
        return true;
    }
}
