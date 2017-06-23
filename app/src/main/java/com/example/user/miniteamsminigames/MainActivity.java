package com.example.user.miniteamsminigames;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.user.miniteamsminigames.PointsActivity.mediaPlayer;

public class MainActivity extends AppCompatActivity {
    public static SharedPreferences piano_pref;
    public static SharedPreferences slide_pref;
    public static SharedPreferences tap_pref;
    MediaPlayer mediaPlayer;
    public MediaPlayer phone_music;
    public static Typeface tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        phone_music = MediaPlayer.create(getApplicationContext(), R.raw.mainmenu);
        phone_music.setLooping(true);
        phone_music.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tap_pref = getSharedPreferences("tap1", Context.MODE_PRIVATE);
        piano_pref = getSharedPreferences("piano", Context.MODE_PRIVATE);
        slide_pref = getSharedPreferences("slide", Context.MODE_PRIVATE);
        tf = Typeface.createFromAsset(getAssets(),
                "fonts/PRICEDOW.TTF");
        //mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.lol);
        //mediaPlayer.start();
        Button start = (Button)findViewById(R.id.start);
        start.setTypeface(tf);
        start.setText("Start Game");
        View.OnClickListener listenerStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone_music.stop();
                Button button = (Button) v;
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        };
        start.setOnClickListener(listenerStart);
        Button options = (Button)findViewById(R.id.options);
        options.setTypeface(tf);
        options.setText("Records");
        View.OnClickListener listenerOptions = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                Intent intent = new Intent(MainActivity.this, RecordsActivity.class);
                startActivity(intent);
            }
        };
        options.setOnClickListener(listenerOptions);

    }

    @Override
    public void onBackPressed() {
        if (phone_music.isPlaying()) {
            phone_music.stop();
        }
        finish();
        super.onBackPressed();
    }
}
