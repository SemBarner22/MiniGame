package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class SliderAct extends AppCompatActivity {
    ImageButton pause;
    public static State st;

    @Override
    protected void onPause() {
        st = SliderView.state;
        SliderView.state = State.PAUSED;
        pause.setVisibility(View.INVISIBLE);
        super.onPause();
    }

    @Override
    protected void onPostResume() {
        SliderView.state = st;
        pause.setVisibility(View.VISIBLE);
        super.onPostResume();
    }

    public static MediaPlayer lose_music;
    public static MediaPlayer phon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lose_music = MediaPlayer.create(getApplicationContext(), R.raw.wasted);
        phon = MediaPlayer.create(getApplicationContext(), R.raw.slidermusic);
        phon.start();
        setContentView(R.layout.activity_slide);
        pause = (ImageButton) findViewById(R.id.pause);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SliderAct.this, PauseSliderActivity.class);
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
