package com.example.user.miniteamsminigames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PointsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Points(this));
    }
}