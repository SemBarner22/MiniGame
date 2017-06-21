package com.example.user.miniteamsminigames;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.user.miniteamsminigames.MainActivity.tf;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button btns = (Button)findViewById(R.id.btns);
        btns.setTypeface(tf);
        btns.setText("Button Click");
        View.OnClickListener listenerStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, ButtonsActivity.class);
                startActivity(intent);
            }
        };

        btns.setOnClickListener(listenerStart);
        Button btnslide = (Button)findViewById(R.id.btnslide);
        btnslide.setTypeface(tf);
        btnslide.setText("Tap Tap");
        View.OnClickListener listenerOptions = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, TapActivity.class);
                startActivity(intent);
            }
        };
        btnslide.setOnClickListener(listenerOptions);
        Button piano = (Button)findViewById(R.id.piano);
        piano.setTypeface(tf);
        piano.setText("Piano Tiles");
        View.OnClickListener listenerExit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, PointsActivity.class);
                startActivity(intent);
            }
        };
        piano.setOnClickListener(listenerExit);
        Button tap = (Button)findViewById(R.id.tap_tap);
        tap.setTypeface(tf);
        tap.setText("Slider");
        View.OnClickListener listenerStart1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, SliderAct.class);
                startActivity(intent);
            }
        };
        tap.setOnClickListener(listenerStart1);
    }
}

