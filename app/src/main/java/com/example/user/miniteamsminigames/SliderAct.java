package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class SliderAct extends AppCompatActivity {
    public static Button tv;
    public static ImageButton pause;
    public static Button menue;
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
        setContentView(R.layout.activity_slide);
        menue = (Button) findViewById(R.id.menu);
        menue.setVisibility(View.VISIBLE);
        View.OnClickListener men = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        };
        menue.setOnClickListener(men);
        tv = (Button) findViewById(R.id.tv);
        View.OnClickListener list = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SliderView p = (SliderView) findViewById(R.id.slide);
                p.restart();
//                finish();
//                Intent intent = new Intent(PointsActivity.this, PointsActivity.class);
//                startActivity(intent);
            }
        };
        tv.setOnClickListener(list);
        tv.setVisibility(View.INVISIBLE);
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
