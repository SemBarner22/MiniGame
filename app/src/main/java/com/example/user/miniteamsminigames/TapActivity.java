package com.example.user.miniteamsminigames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);
        ImageButton pause = (ImageButton) findViewById(R.id.pause);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TapActivity.this, PausePointsActivity.class);
                startActivity(intent);
            }
        };
        pause.setOnClickListener(listener);
    }
}
