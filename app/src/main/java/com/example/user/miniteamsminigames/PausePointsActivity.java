package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static com.example.user.miniteamsminigames.PointsActivity.music_in_game;
import static com.example.user.miniteamsminigames.PointsActivity.p;

public class PausePointsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointspause);
        music_in_game.pause();
        Button start = (Button)findViewById(R.id.resume);
        //start.setText("resume");
        View.OnClickListener listenerStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                music_in_game.start();
                finish();
            }
        };
        start.setOnClickListener(listenerStart);
        Button options = (Button)findViewById(R.id.restart);
        //options.setText("restart");
        Log.d("top", " " + 1);
        View.OnClickListener listenerOptions = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
                Intent intent = new Intent(PausePointsActivity.this, PointsActivity.class);
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
                //Intent intent = new Intent(PausePointsActivity.this, MainActivity.class);
                //startActivity(intent);
            }
        };
        exit.setOnClickListener(listenerExit);
    }
}
