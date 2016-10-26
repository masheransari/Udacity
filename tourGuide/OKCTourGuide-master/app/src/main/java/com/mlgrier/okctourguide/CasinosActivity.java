package com.mlgrier.okctourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class CasinosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_list);

        //Creating a ArrayList: Using String for words
        final ArrayList<Details> detail = new ArrayList<Details>();
        //Elements below should be added to the array list

        /*numberWords.add("one"); detail.add(new Details("Greek Festival",
        "Sep 23-25", R.drawable.number_one, R.raw.number_one));
         */
        detail.add(new Details("Bricktown Brewery", "1 N. Oklahoma Ave. OKC, OK 73104"));
        detail.add(new Details("Coyote Ugly", "121 E. California Ave OKC, OK 73104"));
        detail.add(new Details("Club One 15", "115 E. Sheridan OKC, OK 73104"));
        detail.add(new Details("Mojo's Blues Club", "1 E. California Ave OKC, OK 73104"));
        detail.add(new Details("Captain Norm's Dockside Bar", "105 E. California OKC, OK 73104"));
        detail.add(new Details("Remington Park Racing Casino", "One Remington Place OKC, OK 73111"));
        detail.add(new Details("Room 222", "222 E Sheridan Ave OKC, OK 73104"));
        detail.add(new Details("Wormy Dog Saloon", "311 E Sheridan Ave OKC, OK 73104"));
        detail.add(new Details("The Pump Bar", "2425 N Walker Ave. OKC, OK 73103"));
        detail.add(new Details("Vast", "333 W. Sheridan Ave OKC, OK 73102"));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        // This code with a few changes can be used for a GridView also
        DetailsAdapter adapter = new DetailsAdapter(this, detail, R.color.categoryCasinos);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // details_list.xml file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(adapter);





    }
}
