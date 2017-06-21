package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.process;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/PRICEDOW.TTF");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.lol);
        //mediaPlayer.start();
        Button start = (Button)findViewById(R.id.start);
        start.setTypeface(tf);
        start.setText("Start Game");
        View.OnClickListener listenerStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        };
        start.setOnClickListener(listenerStart);
        Button options = (Button)findViewById(R.id.options);
        options.setTypeface(tf);
        options.setText("Options");
        View.OnClickListener listenerOptions = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                // Intent intent = new Intent(MainActivity.this, OptionActivity.class);
                // startActivity(intent);
            }
        };
        options.setOnClickListener(listenerOptions);
        Button exit = (Button)findViewById(R.id.exit);
        exit.setTypeface(tf);
        exit.setText("Exit");
        View.OnClickListener listenerExit = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        exit.setOnClickListener(listenerExit);
    }

}
