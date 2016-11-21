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

public class videoSubscriber extends AppCompatActivity {
    MediaPlayer media;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_subscriber);

        int orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        // or = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        setRequestedOrientation(orientation);

        mediaController = new MediaController(videoSubscriber.this);
        VideoView videoView = (VideoView) findViewById(R.id.video_view_subcribe);

        String recieverData = null;
        Intent recieve = getIntent();


        recieverData = recieve.getStringExtra("subscriber");

        if (!recieverData.equals("")) {
            String path = null;
            if (recieverData.equals("0")) {
                Log.e("videoHome", "in 0");
                //nani
                path = "alphabat_advanture.mp4";
            } else if (recieverData.equals("1")) {
                Log.e("videoHome", "in 1");
//                aloo
                path = "bulbul_ka_bacha.mp4";
            } else if (recieverData.equals("2")) {
                Log.e("videoHome", "in 2");
//              chanda..
                path = "dora_alphabat.mp4";
            } else if (recieverData.equals("3")) {
                Log.e("videoHome", "in 3");
//                jonny jonny
                path = "humti_dumti.mp4";
            } else if (recieverData.equals("4")) {
                Log.e("videoHome", "in 4");
//              lakdi ki kati
                path = "ringa_ringa_roses.mp4";
            } else if (recieverData.equals("5")) {
                Log.e("videoHome", "in 5");
//              bandar
                path = "twinkle_twinkle.mp4";
            } else if (recieverData.equals("6")) {
                Log.e("videoHome", "in 6");
                path = "tv_number.mp4";
            } else if (recieverData.equals("7")) {
                Log.e("videoHome", "in 7");
                path = "machli_jal_ki_rani.mp4";
            } else {
                Log.e("videoHome", "in 8");
                path = "machli_ka_bacha.mp4";
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
                Toast.makeText(videoSubscriber.this, "Video Didnot Exists\nPlease Check in File Manager on poems Folder first!!", Toast.LENGTH_LONG).show();
            }

        }


    }
}
