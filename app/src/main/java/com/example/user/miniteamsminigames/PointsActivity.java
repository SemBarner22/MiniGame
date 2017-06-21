package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PointsActivity extends AppCompatActivity {
    public static Button tv;
    public static MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/PRICEDOW.TTF");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.wasted);
        tv = (Button) findViewById(R.id.tv);
        tv.setTextColor(Color.RED);
        tv.setTypeface(tf);
        //tv.setVisibility(View.INVISIBLE);
        tv.setText("239");
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
}