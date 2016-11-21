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
public class subscribe extends Fragment {
    public subscribe() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_list, container, false);
        ArrayList<CustomClass> arrayList = new ArrayList<>();
        arrayList.add(new CustomClass("Alphabat Advanture, Come & Take a Journey Of Alphabat World", R.drawable.alphabat_advanture, "poems - 100 Views - 3 days ago.", (float) 1.5));
        arrayList.add(new CustomClass("Bulbul Ka Bacha, Gata Tha gane, Pita Tha Pani...", R.drawable.bulbul_ka_bacha, "Nursury Poem - 1 - 6 Min ago", (float) 7.9));
        arrayList.add(new CustomClass("Dora Explror! DOra Alphabat Exploror, Forest Adventure", R.drawable.dora_alphabat, "Inovatinve Mind - 500K Views - 2 Year ago", (float) 4.7));
        arrayList.add(new CustomClass("Humti Dumti Set on a Wall, Hamti Dumti Had a Great Fall", R.drawable.humti_dumti, "Eduacation Information - 3.5K Views - 4 months ago", (float) 0.9));
        arrayList.add(new CustomClass("Ringa Ringa Roses A Packet Full Of Roses, A Tissue- A Tissue", R.drawable.ringa_ringa, "Nursury Poem - 200K Views - 1 Year ago", (float) 4.1));
        arrayList.add(new CustomClass("Twinkle Twinkle Little Start, How Ever Wonder What You Are..", R.drawable.twinkle_twinkle, "Education Poems - 10K Views - 1 month ago", (float) 7.8));
        arrayList.add(new CustomClass("Let Us Learn The Number, 1 2 3 4 5 6 7 8 9 10 ...",R.drawable.tv_number,"Education - 5 Views - 2 Days Ago",(float)5.8));
        arrayList.add(new CustomClass("Machli Jal Ki Rani Hai, Jivan Us ka Pani Hai.",R.drawable.machli_jal_ki_rani,"Efficient Childern - 235K Views - 3 years ago",(float)7.1));
        arrayList.add(new CustomClass("Machli Ka Bacha, Pani Me Uthra",R.drawable.machli_ka_bacha,"Hindi Poem - 100 Views - 2 years ago",(float)2.4));
        YouTubeAdapter adapter = new YouTubeAdapter(getActivity(), arrayList);
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("videoClass", "position = " + position);
                Intent i = new Intent(subscribe.this.getContext(), videoSubscriber.class);
                i.putExtra("subscriber", String.valueOf(position));
                startActivity(i);
            }
        });

        return rootView;
    }
}
