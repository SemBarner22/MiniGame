package com.example.user.miniteamsminigames;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ImageButton btns = (ImageButton)findViewById(R.id.btns);
        btns.setText("Button Click");
        View.OnClickListener listenerStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, ButtonsActivity.class);
                startActivity(intent);
            }
        };
        btns.setOnClickListener(listenerStart);
        ImageButton btnslide = (ImageButton)findViewById(R.id.btnslide);
        btnslide.setText("Slider");
        View.OnClickListener listenerOptions = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, SliderAct.class);
                startActivity(intent);
            }
        };
        btnslide.setOnClickListener(listenerOptions);
        ImageButton piano = (ImageButton)findViewById(R.id.piano);
        piano.setText("Piano");
        View.OnClickListener listenerExit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, PointsActivity.class);
                startActivity(intent);
            }
        };
        piano.setOnClickListener(listenerExit);
        ImageButton tap = (ImageButton)findViewById(R.id.tap_tap);
        tap.setText("Tap");
        View.OnClickListener listenerStart1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, TapActivity.class);
                startActivity(intent);
            }
        };
        tap.setOnClickListener(listenerStart1);
    }
}

