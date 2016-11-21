package com.example.asheransari.youtubeapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by asher.ansari on 11/17/2016.
 */
public class home extends Fragment{
    public home(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_list, container,false);

        ArrayList<CustomClass> arrayList = new ArrayList<>();
        arrayList.add(new CustomClass("Nani Teri Morni Ko Mor Lay Gaye\nBaki Jo Bacha Tha Kale Chor le Gye", R.drawable.nani,"Urdu Kids - 100K Views - 3 Week ago.",(float)5.5));
        arrayList.add(new CustomClass("Aloo Miya Aloo Miya Kaha Gye The",R.drawable.alo_miya,"childern poem - 50K Views - 6 Month ago",(float)2.5));
        arrayList.add(new CustomClass("Chanda Mama Dur Ke",R.drawable.chanda,"childern poem - 500K Views - 2 Year ago",(float)5.7));
        arrayList.add(new CustomClass("Jonny Jonny Yes Papa Eating Sugar No Papa",R.drawable.sugar,"Childern Education - 10 Views - 11 month ago",(float)1.5));
        arrayList.add(new CustomClass("Lakdi Ki Kati Kati Pe Ghora\nGhore ki Dum Pe Jo Mara Hathora",R.drawable.lakdi,"Education Poem - 200 Views - 1 Year ago",(float)3.5));
        arrayList.add(new CustomClass("Ek Bandar Ne Kholi Dukan\nAye Grahak Bhe Aise Mahan",R.drawable.bandar,"Urdu Maza - 100K Views - 1 day ago",(float)4.7));

        YouTubeAdapter adapter = new YouTubeAdapter(getActivity(), arrayList);
        ListView listView = (ListView)rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("videoClass","position = "+position);
                Intent i = new Intent(home.this.getContext(),videoHome.class);
                i.putExtra("home", String.valueOf(position));
                startActivity(i);
            }
        });

        return rootView;
    }
}
