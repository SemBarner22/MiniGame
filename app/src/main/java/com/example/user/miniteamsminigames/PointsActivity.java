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

public class PointsActivity extends AppCompatActivity {
    public static Button tv;
    public double rememberV;
    public int rememberk;

    @Override
    protected void onPause() {
        rememberk = Points.k;
        rememberV = Points.V;
        Points.V = 0;
        Points.k = 0;
        super.onPause();
    }

    public static Typeface tf;
    public static MediaPlayer mediaPlayer;
    public static MediaPlayer music_in_game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        tf = Typeface.createFromAsset(getAssets(),
                "fonts/PRICEDOW.TTF");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wasted);
        music_in_game = MediaPlayer.create(getApplicationContext(), R.raw.gdpiano);
        music_in_game.start();
        tv = (Button) findViewById(R.id.tv);
        tv.setTextColor(Color.RED);
        tv.setTypeface(tf);
        tv.setVisibility(View.INVISIBLE);
        ImageButton pause = (ImageButton) findViewById(R.id.pause);
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
        Points.k = rememberk;
        Points.V = rememberV;
        super.onPostResume();
    }
}