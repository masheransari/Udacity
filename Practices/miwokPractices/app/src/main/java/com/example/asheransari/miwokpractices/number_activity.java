package com.example.asheransari.miwokpractices;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class number_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new numberFragement())
                .commit();
    }
}

//import android.content.Context;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//public class number_activity extends AppCompatActivity {
//
//    private AudioManager audioManager;
//    private MediaPlayer mediaPlayer;
//
//    AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
//        @Override
//        public void onAudioFocusChange(int focusChange) {
//            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange ==  AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK)
//            {
//                mediaPlayer.pause();
//                mediaPlayer.seekTo(0);
//            }
//            else if (focusChange== AudioManager.AUDIOFOCUS_GAIN)
//            {
//                mediaPlayer.start();
//            }
//            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS)
//            {
//                releasePlayer();
//            }
//        }
//    };
//private MediaPlayer.OnCompletionListener onCompletionListener = (new MediaPlayer.OnCompletionListener() {
//    @Override
//    public void onCompletion(MediaPlayer mp) {
//        releasePlayer();
//    }
//});
////  naheed baji rozi..
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.word_list);
////        getActionBar().setDisplayHomeAsUpEnabled(true);
//    final ArrayList<variableClass> variableClasses =  new ArrayList<variableClass>();
//
//        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//        variableClasses.add(new variableClass("one", "lutti", R.drawable.number_one,R.raw.number_one));
//        variableClasses.add(new variableClass("two", "otiiko", R.drawable.number_two,R.raw.number_two));
//        variableClasses.add(new variableClass("three", "tolookosu", R.drawable.number_three,R.raw.number_three));
//        variableClasses.add(new variableClass("four", "oyyisa", R.drawable.number_four,R.raw.number_four));
//        variableClasses.add(new variableClass("five", "massokka", R.drawable.number_five,R.raw.number_five));
//        variableClasses.add(new variableClass("six", "temmokka", R.drawable.number_six,R.raw.number_six));
//        variableClasses.add(new variableClass("seven", "kenekaku", R.drawable.number_seven,R.raw.number_seven));
//        variableClasses.add(new variableClass("eight", "kawinta", R.drawable.number_eight,R.raw.number_eight));
//        variableClasses.add(new variableClass("nine", "wo'e", R.drawable.number_nine,R.raw.number_nine));
//        variableClasses.add(new variableClass("ten", "na'aacha", R.drawable.number_ten,R.raw.number_ten));
//        WordAdapter adapter = new WordAdapter(this, variableClasses, R.color.number);
//
//        ListView listView = (ListView)findViewById(R.id.list);
//
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                variableClass variable = variableClasses.get(position);
//
//                releasePlayer();
//
//                int result = audioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED)
//                {
//                    mediaPlayer = MediaPlayer.create(number_activity.this, variable.getmAudioResourceID());
//                    mediaPlayer.start();
//                }
//                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        releasePlayer();
//                        Toast.makeText(number_activity.this, "I am Done", Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//            }
//        });
//
//    }
//
//    @Override
//    public void onStop()
//    {
//        super.onStop();
//        releasePlayer();
//    }
//
//    private void releasePlayer()
//    {
//        if (mediaPlayer != null)
//        {
//            mediaPlayer.release();
//            mediaPlayer= null;
//            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
//        }
//    }
//}
