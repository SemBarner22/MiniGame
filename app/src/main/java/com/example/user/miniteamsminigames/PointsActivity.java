package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import static com.example.user.miniteamsminigames.MainActivity.tf;

public class PointsActivity extends AppCompatActivity {
    public static Button tv;
    public double rememberV;
    public int rememberk;
    public static boolean sound;
    public static boolean music;
    public static Points p;
    public static Button menu;
    public static ImageButton pause;

    @Override
    protected void onPause() {
        if (mediaPlayer.isPlaying()) {
            sound = true;
            mediaPlayer.stop();
        }
        if (music_in_game.isPlaying()) {
            music = true;
            music_in_game.stop();
        }
        rememberk = Points.k;
        rememberV = p.V;
        pause.setVisibility(View.INVISIBLE);
        p.V = 0;
        Points.k = 0;
        super.onPause();
    }

    public static MediaPlayer mediaPlayer;
    public static MediaPlayer music_in_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wasted);
        music_in_game = MediaPlayer.create(getApplicationContext(), R.raw.gdpiano);
        p = (Points) findViewById(R.id.points);
        tv = (Button) findViewById(R.id.tv);
        menu = (Button) findViewById(R.id.menu);
        View.OnClickListener men = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
        menu.setOnClickListener(men);
        menu.setVisibility(View.INVISIBLE);
        View.OnClickListener list = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Points p = (Points) findViewById(R.id.points);
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
                Intent intent = new Intent(PointsActivity.this, PausePointsActivity.class);
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
    protected void onPostResume() {
        if (music) {
            music = false;
            music_in_game.start();
        }
        if (sound) {
            sound = false;
            mediaPlayer.start();
        }
        Points.k = rememberk;
        p.V = rememberV;
        pause.setVisibility(View.VISIBLE);
        super.onPostResume();
    }
    @Override
    public void onBackPressed() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        if (music_in_game.isPlaying()) {
            music_in_game.stop();
            music_in_game.reset();
            try {
                music_in_game.prepareAsync();
                music_in_game.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        music_in_game.seekTo(0);
                        music_in_game.setLooping(true);
                        music_in_game.start();
                    }
                });
            } catch (IllegalStateException e) {
                music_in_game.seekTo(0);
                music_in_game.setLooping(true);
                music_in_game.start();
            }
        }
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}