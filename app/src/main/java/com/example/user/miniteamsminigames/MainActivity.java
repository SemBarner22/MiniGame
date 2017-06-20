package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton start = (ImageButton)findViewById(R.id.start);
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
        ImageButton options = (ImageButton)findViewById(R.id.options);
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
        options.setOnClickListener(listenerOptions);
        Button exit = (Button)findViewById(R.id.exit);
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
