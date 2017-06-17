package com.example.user.miniteamsminigames;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class ButtonsActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout game = new FrameLayout(this);
        Buttons gameview = new Buttons(this);
        LinearLayout gameWidgets = new LinearLayout(this);

        Button endGameButton = new Button(this);
        endGameButton.setWidth(300);
        endGameButton.setText("Start Game");

        gameWidgets.addView(endGameButton);
        game.addView(gameview);
        game.addView(gameWidgets);
        setContentView(game);
        endGameButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }

}

