package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.user.miniteamsminigames.TapActivity.tap_music;

public class PauseTapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tappause);
        tap_music.pause();
        Button start = (Button)findViewById(R.id.resume);
        //start.setText("resume");
        View.OnClickListener listenerStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tap_music.start();
                finish();
            }
        };
        start.setOnClickListener(listenerStart);
        Button options = (Button)findViewById(R.id.restart);
        //options.setText("restart");
        View.OnClickListener listenerOptions = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
                Intent intent = new Intent(PauseTapActivity.this, TapActivity.class);
                startActivity(intent);
            }
        };
        options.setOnClickListener(listenerOptions);
        options.setOnClickListener(listenerOptions);
        Button exit = (Button)findViewById(R.id.exit);
        //exit.setText("Main menu");
        View.OnClickListener listenerExit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
                //Intent intent = new Intent(PauseTapActivity.this, MainActivity.class);
                //startActivity(intent);
            }
        };
        exit.setOnClickListener(listenerExit);
    }
}