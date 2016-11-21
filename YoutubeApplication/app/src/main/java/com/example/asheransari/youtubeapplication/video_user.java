package com.example.asheransari.youtubeapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

public class video_user extends AppCompatActivity {

    MediaPlayer media;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_user);

        int orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        // or = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        setRequestedOrientation(orientation);

        mediaController = new MediaController(video_user.this);
        VideoView videoView = (VideoView) findViewById(R.id.video_view_user);



        String recieverData = null;
        Intent recieve = getIntent();


        recieverData = recieve.getStringExtra("user");

        if (!recieverData.equals("")) {
            String path = null;
            if (recieverData.equals("0")) {
                Log.e("videoHome", "in 0");
                //nani
                path = "ao_mere_sath_khao_mere_sath.mp4";
            } else if (recieverData.equals("1")) {
                Log.e("videoHome", "in 1");
//                aloo
                path = "dhamaka_hoga.mp4";
            } else if (recieverData.equals("2")) {
                Log.e("videoHome", "in 2");
//              chanda..
                path = "jungle_mein_mungle.mp4";
            } else if (recieverData.equals("3")) {
                Log.e("videoHome", "in 3");
//                jonny jonny
                path = "lab_pe_ati.mp4";
            } else if (recieverData.equals("4")) {
                Log.e("videoHome", "in 4");
//              lakdi ki kati
                path = "left_right_left_right.mp4";
            } else if (recieverData.equals("5")) {
                Log.e("videoHome", "in 5");
//              bandar
                path = "mujay_dusman.mp4";
            } else {
                Log.e("videoHome", "in 6");
                path = "pata_kya_phochta_hai.mp4";
            }

            Uri uri = Uri.parse(Environment.getExternalStorageDirectory() + "/poems/" + path);

            File myFile = new File(Environment.getExternalStorageDirectory() + "/poems/"+path);

            if (myFile.exists())
            {
                videoView.setVideoURI(uri);

                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.start();
            }
            else
            {
                Toast.makeText(video_user.this, "Video Didnot Exists\nPlease Check in File Manager on poems Folder first!!", Toast.LENGTH_LONG).show();
            }

        }


    }
}
