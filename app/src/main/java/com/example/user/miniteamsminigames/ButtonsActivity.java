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
    public static Button endGameButton;
    public static LinearLayout gameWidgets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FrameLayout game = new FrameLayout(this);
        Buttons gameview = new Buttons(this);
        gameWidgets = new LinearLayout(this);
        endGameButton = new Button(this);
        endGameButton.setWidth(300);
        //endGameButton.setText("Start Game");
        endGameButton.setText("Main menu");
        endGameButton.setVisibility(View.INVISIBLE);
        //gameWidgets.addView(endGameButton);
        endGameButton.setOnClickListener(this);
        game.addView(gameview);
        game.addView(gameWidgets);
        setContentView(game);
    }


    @Override
    public void onClick(View v) {
        finish();
        //Intent intent = new Intent(this, Main2Activity.class);
        //startActivity(intent);
    }

}

