package com.example.user.miniteamsminigames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by User on 16.06.2017.
 */

public class Points extends View{
    private static final Paint bg = new Paint();
    private static final Paint text = new Paint();
    private ArrayList<Square> squares = new ArrayList<>();
    public float V = 50;
    public Square currentSquare = null;
    public int score = 0;
    public State state = State.NOT_LOSE;
    public Points(Context context) {
        super(context);
        bg.setColor(0xff75c1ff);
        text.setColor(0xffff0000);
        text.setTextAlign(Paint.Align.CENTER);
        squares = new ArrayList<>();
        squares.add(new Square(0, 0, 50, 100));
        currentSquare = squares.get(0);
        score = 0;
        text.setTextSize(getWidth() / 6);
        new MyTimer(1000L, 30).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), bg);
        for (Square s:squares) {
            s.draw(canvas);
        }
        if (state == State.LOSE)
            canvas.drawText("Score: " + score, getWidth() / 2, getHeight() / 2,
                    text);
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (event.getX() > currentSquare.x && event.getX() < currentSquare.x + currentSquare.w
                    && event.getY() > currentSquare.y && event.getY() < currentSquare.y + currentSquare.h) {
                score++;
                squares.remove(0);
                currentSquare = squares.get(0);
            }
            else
                state = State.LOSE;
        }
        return true;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        squares = new ArrayList<>();
        squares.add(new Square(0, 0, 50, 100));
        currentSquare = squares.get(0);
        score = 0;
        text.setTextSize(w / 6);
        new MyTimer(1000L, 30).start();
    }
    class MyTimer extends CountDownTimer {
        int k = 0;
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public void onTick(long millisUntilFinished) {
            k++;
            k = 0;
            for (Square s : squares) {
                s.y += V;
            }
            if (currentSquare.y > getHeight()) {
                state = State.LOSE;
            }
            if (k >= 50) {
                int x = (int) Math.random() * getWidth();
                squares.add(new Square(x, 0, x + 50, 100));
                k = 0;
            }
        }

        @Override
        public void onFinish() {
        }

    }
}
