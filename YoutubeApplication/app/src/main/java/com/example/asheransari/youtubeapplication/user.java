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
public class user extends Fragment {
    public user(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_list,container,false);

        ArrayList<CustomClass> arrayList = new ArrayList<>();
        arrayList.add(new CustomClass("Ao Mere Pass ur Khao Mere Sath, Yeh Duniya Hai Tumhari Chahe Gande Ho Tumhare Hath..", R.drawable.ao_mere_sath_khao_mere_7,"Commander Safeguard - 1K Views - 1 Year ago.",(float)7.5));
        arrayList.add(new CustomClass("Dhamaka Hoga Panch Ka, Panch Bimariyo Ka Hoga.. Patacka Panch Ka",R.drawable.dhamaka_hoga,"Commander Safeguard - 502 Views - 9 Month ago",(float)3.5));
        arrayList.add(new CustomClass("Jungle Mein Mangal La_Nazre Juhka Na, Phela De Scandle Through Social Bundle",R.drawable.jungle_mein_mangle,"Dartu 2016 - 500K Views - 2 Week ago",(float)6.7));
        arrayList.add(new CustomClass("lab Pe Ati Hai Dua Ban ke Tamna Meri..",R.drawable.lab_pe_ati_ha_dua,"Pakistan Customs - +990K Views - 5 Year ago",(float)9.5));
        arrayList.add(new CustomClass("Dartu ne Jeete Hai Fight Right Right, Left Right Left Right",R.drawable.left_right,"Commander Safeguard - 200K Views - 1 Year ago",(float)4.5));
        arrayList.add(new CustomClass("Mujay Dusman Ke Bacho Ko Parhana Hai..",R.drawable.mujay_dusman_ke,"Peshwar Pakistan - +800K Views - 5 Year  ago",(float)9.7));
        arrayList.add(new CustomClass("Pata Kia Phoch Ta Hai Tu, Kitaboo Me Dhudunga Me.",R.drawable.pata_kya_phochta,"Urdu Maza - 100K Views - 4 Year ago",(float)8.7));

        YouTubeAdapter adapter = new YouTubeAdapter(getActivity(), arrayList);
        ListView listView = (ListView)rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);
        Intent i = new Intent(user.this.getContext(),videoHome.class);
//                i.putExtra("user", String.valueOf(position));
//                startActivity(i);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("videoClass","position = "+position);
                Intent i = new Intent(user.this.getContext(),video_user.class);
                i.putExtra("user", String.valueOf(position));
                startActivity(i);
            }
        });


        return rootView;
    }
}
