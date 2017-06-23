package com.example.user.miniteamsminigames;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import static com.example.user.miniteamsminigames.MainActivity.piano_pref;
import static com.example.user.miniteamsminigames.MainActivity.slide_pref;
import static com.example.user.miniteamsminigames.MainActivity.tap_pref;
import static com.example.user.miniteamsminigames.MainActivity.tf;

public class RecordsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Button tap = (Button) findViewById(R.id.tap);
        tap.setTypeface(tf);
        tap.setText("TAP HIGHSCORE " + tap_pref.getInt("tap1", 0));
        Button piano = (Button) findViewById(R.id.piano);
        piano.setTypeface(tf);
        piano.setText("PIANO HIGHSCORE " + piano_pref.getInt("piano", 0));
        Button slide = (Button) findViewById(R.id.slide);
        slide.setTypeface(tf);
        slide.setText("SLIDER HIGHSCORE " + slide_pref.getInt("slide", 0));
    }
}
