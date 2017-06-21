package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TapActivity extends AppCompatActivity {
    public static MediaPlayer tap_music;
    public static MediaPlayer loser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tap_music = MediaPlayer.create(getApplicationContext(), R.raw.taptap);
        loser = MediaPlayer.create(getApplicationContext(), R.raw.wasted);
        tap_music.start();
        setContentView(R.layout.activity_tap);
        ImageButton pause = (ImageButton) findViewById(R.id.pause);
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
}
