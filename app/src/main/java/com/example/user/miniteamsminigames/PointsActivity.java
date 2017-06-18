package com.example.user.miniteamsminigames;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class PointsActivity extends AppCompatActivity {
    public static Button endGameButton;
    public static LinearLayout gameWidgets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout game = new FrameLayout(this);
        Points gameview = new Points(this);
        gameWidgets = new LinearLayout(this);
    //    endGameButton = new Button(this);
    //    endGameButton.setWidth(300);
        // endGameButton.setText("Start Game");
        game.addView(gameWidgets);
        game.addView(gameview);
        setContentView(game);
    }
}