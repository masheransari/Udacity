package com.mlgrier.okctourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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
        detail.add(new Details("Andy Alligator's Fun Park", "301 W. Reno Ave. OKC, OK 73102", R.drawable.andy));
        detail.add(new Details("Acme Brick Park", "2716 N.E. 50th St. OKC, OK 73111", R.drawable.brickpark));
        detail.add(new Details("Chester's Party Farm", "725 S. Lincoln Blvd OKC, OK 73102", R.drawable.farm));
        detail.add(new Details("Celebration Station", "2020 Remington Pl. OKC, OK 73111", R.drawable.station));
        detail.add(new Details("GattiTown Fun Center", "2101 N.E. 50th St. OKC, OK 73111", R.drawable.gatti));
        detail.add(new Details("Great Balls Of Fire Bowling", "10301 S. Sunnyland Rd. OKC, OK 73160", R.drawable.great));
        detail.add(new Details("Brickopolis", "101 S. Mickey Mantle Dr. OKC, OK 73104", R.drawable.brick));
        detail.add(new Details("HeyDay Center", "2905 N.W. 36th St. OKC, OK 73112", R.drawable.heyday));
        detail.add(new Details("Frontier City Theme Park", "11501 N. I-35. OKC, Ok 73131", R.drawable.frontier));
        detail.add(new Details("White Water Bay", "3908 W. Reno Ave. OKC, OK 73107", R.drawable.white));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        // This code with a few changes can be used for a GridView also
        DetailsAdapter adapter = new DetailsAdapter(this, detail, R.color.categoryFamily);

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
