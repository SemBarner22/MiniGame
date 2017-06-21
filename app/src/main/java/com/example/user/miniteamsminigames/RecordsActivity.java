package com.example.user.miniteamsminigames;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import static com.example.user.miniteamsminigames.MainActivity.tf;

public class RecordsActivity extends AppCompatActivity {
    public static SharedPreferences tap_pref;
    public static SharedPreferences piano_pref;
    public static SharedPreferences slide_pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        tap_pref = getSharedPreferences("tap", Context.MODE_PRIVATE);
        Button tap = (Button) findViewById(R.id.tap);
        tap.setTypeface(tf);
        tap.setText("HIGHSCORE " + Float.toString((tap_pref.getFloat("tap", 0))));

        piano_pref = getSharedPreferences("piano", Context.MODE_PRIVATE);
        Button piano = (Button) findViewById(R.id.piano);
        piano.setTypeface(tf);
        piano.setText("HIGHSCORE " + piano_pref.getInt("piano", 0));

        slide_pref = getSharedPreferences("slide", Context.MODE_PRIVATE);
        Button slide = (Button) findViewById(R.id.slide);
        slide.setTypeface(tf);
        slide.setText("HIGHSCORE " + slide_pref.getInt("slide", 0));
    }
}
