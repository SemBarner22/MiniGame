package com.example.user.miniteamsminigames;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;

import static com.example.user.miniteamsminigames.ButtonsActivity.endGameButton;
import static com.example.user.miniteamsminigames.ButtonsActivity.gameWidgets;


/**
 * Created by User on 16.06.2017.
 */

public class Buttons extends View {
    private static final Paint color1 = new Paint();
    private static final Paint color2 = new Paint();
    private static final Paint text = new Paint();
    private static final long INF = 1000000000000000000L;
    private int l;
    State state = State.WAITING;
    private int score = 0;
    private long scoreFinal = INF;

    public Buttons(Context context) {
        super(context);
        Log.d("1", "kek");
        color1.setColor(Color.RED);
        color2.setColor(Color.GREEN);
        text.setColor(Color.BLACK);
        text.setTextAlign(Paint.Align.CENTER);
        score = 0;
        state = State.WAITING;
        text.setTextSize(getWidth() / 6);
        l = (int) (Math.random() * 10) + 20;
        new MyTimer(10000L, 30).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (state == State.WAITING)
            canvas.drawRect(0, 0, getWidth(), getHeight(), color1);
        if (state == State.READY_TO_TAP) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), color2);
        }
        if (state == State.LOSE) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), color2);
            if (scoreFinal != INF)
                canvas.drawText("Score: " + scoreFinal, getWidth() / 2, getHeight() / 2, text);
            else
                canvas.drawText("YOU LOST!", getWidth() / 2, getHeight() / 2, text);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (state == State.READY_TO_TAP) {
                scoreFinal = score;
                state = State.LOSE;
                endGameButton.setText("kek");
                gameWidgets.addView(endGameButton);
                Log.d("1", "c");
            } else
                state = State.LOSE;
            //  Intent intent = new Intent(Main2Activity.class, MainActivity.class);
        }
        return true;
    }
    public void restart() {
        score = 0;
        state = State.WAITING;
        text.setTextSize(getWidth() / 6);
        l = (int) (Math.random() * 10) + 20;
        scoreFinal = INF;
        new MyTimer(100000L, 30).start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        score = 0;
        text.setTextSize(w / 6);
        state = State.WAITING;
        text.setTextSize(getWidth() / 6);
        l = (int) (Math.random() * 10) + 20;
        new MyTimer(100000L, 30).start();
    }

    class MyTimer extends CountDownTimer {
        int k = 0;

        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onTick(long millisUntilFinished) {
            k++;
            //  Log.d("l", "a" + k);
            if (k >= l) {
                //    Log.d("l", " " + k);
                if (state == State.WAITING)
                    state = State.READY_TO_TAP;
                score++;
            }
        }

        @Override
        public void onFinish() {
            Log.d("l", "b" + k);
            if (state == State.READY_TO_TAP)
                state = State.LOSE;
            //  restart();
        }

    }


}
