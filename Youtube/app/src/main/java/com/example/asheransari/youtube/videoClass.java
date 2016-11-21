package com.example.asheransari.youtube;

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
public class videoClass extends Fragment{
    public videoClass()
    {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_list,container,false);
//        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
//                + R.raw.your_raw_file);
        ArrayList<uTubeClass> arrayList = new ArrayList<uTubeClass>();
//        arrayList.add(new uTubeClass("Nani Teri Morni Ko Mor Lay Gaye\nBaki Jo Bacha Tha Kale Chor le Gye", Uri.parse("android.resource://" + getActivity().getPackageName() + "/"+R.raw.nani_teri_morni)));
        arrayList.add(new uTubeClass("Nani Teri Morni Ko Mor Lay Gaye\nBaki Jo Bacha Tha Kale Chor le Gye", R.drawable.nani));
        arrayList.add(new uTubeClass("Aloo Miya Aloo Miya Kaha Gye The",R.drawable.alo_miya));
        arrayList.add(new uTubeClass("Chanda Mama Dur Ke",R.drawable.chanda));
        arrayList.add(new uTubeClass("Jonny Jonny Yes Papa Eating Sugar No Papa",R.drawable.sugar));
        arrayList.add(new uTubeClass("Lakdi Ki Kati Kati Pe Ghora\nGhore ki Dum Pe Jo Mara Hathora",R.drawable.lakdi));
        arrayList.add(new uTubeClass("Ek Bandar Ne Kholi Dukan\nAye Grahak Bhe Aise Mahan",R.drawable.bandar));

        uTube_Adapter adapter = new uTube_Adapter(getActivity(),arrayList);
        ListView listView = (ListView)rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("videoClass","position = "+position);
            }
        });

        return rootView;
    }
}
