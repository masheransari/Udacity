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

public class videoHome extends AppCompatActivity {

    MediaPlayer media;
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_home);
        int orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        // or = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        setRequestedOrientation(orientation);

        mediaController = new MediaController(videoHome.this);
        VideoView videoView = (VideoView) findViewById(R.id.video_view_home);



        String recieverData = null;
        Intent recieve = getIntent();


        recieverData = recieve.getStringExtra("home");

        if (!recieverData.equals("")) {
            String path = null;
            if (recieverData.equals("0")) {
                Log.e("videoHome", "in 0");
                //nani
                path = "nani_teri_morni.mp4";
            } else if (recieverData.equals("1")) {
                Log.e("videoHome", "in 1");
//                aloo
                path = "aloo_miya.mp4";
            } else if (recieverData.equals("2")) {
                Log.e("videoHome", "in 2");
//              chanda..
                path = "chanda_mama.mp4";
            } else if (recieverData.equals("3")) {
                Log.e("videoHome", "in 3");
//                jonny jonny
                path = "eating_sugar.mp4";
            } else if (recieverData.equals("4")) {
                Log.e("videoHome", "in 4");
//              lakdi ki kati
                path = "lakdi_ki_kati.mp4";
            } else {
                Log.e("videoHome", "in 5");
//              bandar
                path = "bandar.mp4";
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
                Toast.makeText(videoHome.this, "Video Didnot Exists\nPlease Check in File Manager on poems Folder first!!", Toast.LENGTH_LONG).show();
            }

        }


    }


//
//    public void play()
//    {
//        boolean play = media.isPlaying();
//
//        if (play == true)
//        {
//            Toast.makeText(getBaseContext(),"Video is Playing",Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(getBaseContext(),"Song not Playing",Toast.LENGTH_SHORT).show();
//        }
//    }
}

