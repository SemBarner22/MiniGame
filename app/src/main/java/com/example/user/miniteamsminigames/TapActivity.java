package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TapActivity extends AppCompatActivity {
    public static MediaPlayer tap_music;
    public static MediaPlayer loser;
    public static Button menu;
    public static ImageButton pause;
    public static Button tv;
    public static double rememV;

    @Override
    protected void onPause() {
        Tap_tap.state = State.PAUSED;
        TapActivity.rememV = Tap_tap.V;
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        Tap_tap.V = rememV;
        Tap_tap.state = State.NOT_LOSE;
        super.onPostResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tap_music = MediaPlayer.create(getApplicationContext(), R.raw.taptap);
        loser = MediaPlayer.create(getApplicationContext(), R.raw.wasted);
        setContentView(R.layout.activity_tap);
        menu = (Button) findViewById(R.id.menu);
        View.OnClickListener men = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
        menu.setOnClickListener(men);
        menu.setVisibility(View.INVISIBLE);
        tv = (Button) findViewById(R.id.tv);
        View.OnClickListener list = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tap_tap p = (Tap_tap) findViewById(R.id.tap_tap);
                p.restart();
//                finish();
//                Intent intent = new Intent(PointsActivity.this, PointsActivity.class);
//                startActivity(intent);
            }
        };
        tv.setOnClickListener(list);
        //tv.setTextColor(Color.RED);
        //tv.setTypeface(tf);
        tv.setVisibility(View.INVISIBLE);
        pause = (ImageButton) findViewById(R.id.pause);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TapActivity.this, PauseTapActivity.class);
                startActivityForResult(intent, 1);
            }
        };
        pause.setOnClickListener(listener);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            finish();
        }
    }
    @Override
    public void onBackPressed() {
        if (loser.isPlaying()) {
            loser.stop();
        }
        if (tap_music.isPlaying()) {
            tap_music.stop();
            tap_music.reset();
            try {
                tap_music.prepareAsync();
                tap_music.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        tap_music.seekTo(0);
                        tap_music.setLooping(true);
                        tap_music.start();
                    }
                });
            } catch (IllegalStateException e) {
                tap_music.seekTo(0);
                tap_music.setLooping(true);
                tap_music.start();
            }
        }
        finish();
        Log.d("kek", "kek");
    }
}
