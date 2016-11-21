package com.example.asheransari.youtube;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;

/**
 * Created by asher.ansari on 11/17/2016.
 */
public class uTube_Adapter extends ArrayAdapter<uTubeClass>{

    public uTube_Adapter(Activity activity, ArrayList<uTubeClass> classes)
    {
        super(activity,0,classes);
    }
    @Override
    public View getView(int poistion, View convertView, ViewGroup parent)
    {
        View listItemView = convertView;
        if (listItemView == null)
        {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        uTubeClass currentClass = getItem(poistion);

//        VideoView videoView = (VideoView)listItemView.findViewById(R.id.video_view);
//        videoView.setVideoPath(getContext().getResources().getMovie(currentClass.getVideoView()).toString());
//        videoView.setVideoURI(currentClass.getVideoView());
        ImageView imageView = (ImageView)listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentClass.getimageView());

        TextView textView = (TextView) listItemView.findViewById(R.id.detail);
        textView.setText(currentClass.getDetail());

        return listItemView;
    }
}
