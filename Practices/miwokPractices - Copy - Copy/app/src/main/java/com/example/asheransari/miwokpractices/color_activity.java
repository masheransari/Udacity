package com.example.asheransari.miwokpractices;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class color_activity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    protected AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener =  new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
            {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN)
            {
                mediaPlayer.start();
            }
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
            {
                releasePlayer();
            }
        }
    };
private MediaPlayer.OnCompletionListener onCompletionListener = (new MediaPlayer.OnCompletionListener() {
    @Override
    public void onCompletion(MediaPlayer mp) {
        releasePlayer();
    }
});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
    final ArrayList<variableClass> variableClasses = new ArrayList<variableClass>();
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        variableClasses.add(new variableClass("red", "wetetti",R.drawable.color_red,R.raw.color_red));
        variableClasses.add(new variableClass("mustard yellow", "chiwiita",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        variableClasses.add(new variableClass("dusty yellow", "topiisa",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        variableClasses.add(new variableClass("green", "chokokko",R.drawable.color_green,R.raw.color_green));
        variableClasses.add(new variableClass("brown", "takaakki",R.drawable.color_brown,R.raw.color_brown));
        variableClasses.add(new variableClass("gray", "topoppi",R.drawable.color_gray,R.raw.color_gray));
        variableClasses.add(new variableClass("black", "kululli",R.drawable.color_black,R.raw.color_black));
        variableClasses.add(new variableClass("white", "kelelli",R.drawable.color_white,R.raw.color_white));

        WordAdapter adapter = new WordAdapter(this, variableClasses, R.color.color);

        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                variableClass variableClasses1 = variableClasses.get(position);

                releasePlayer();
                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
                {
                    mediaPlayer = MediaPlayer.create(color_activity.this, variableClasses1.getmAudioResourceID());
                    mediaPlayer.start();
                }
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releasePlayer();
                        Toast.makeText(color_activity.this, "I am Done!!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    public void onStop()
    {
        super.onStop();
        releasePlayer();
    }
    private void releasePlayer()
    {
        if (mediaPlayer!=null)
        {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }
}
