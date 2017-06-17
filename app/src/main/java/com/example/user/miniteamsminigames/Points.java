package com.example.user.miniteamsminigames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
    public double V = 50;
    public Square currentSquare = null;
    public int score = 0;
    public int k = 0;
    public State state = State.NOT_LOSE;
    public Points(Context context) {
        super(context);
        bg.setColor(Color.WHITE);
        text.setColor(Color.BLUE);
        text.setTextAlign(Paint.Align.CENTER);
        squares = new ArrayList<>();
        squares.add(new Square(0, 0, getWidth() / 4, getHeight() / 4));
        currentSquare = squares.get(0);
        score = 0;
        text.setTextSize(getWidth() / 6);
        new MyTimer(1000L, 10).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), bg);
        for (Square s:squares) {
            s.draw(canvas);
        }
        if (state == State.LOSE)
            canvas.drawText("Score: " + score, getWidth() / 2, getHeight() / 2, text);
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
        squares.add(new Square(0, 0, w / 4, h / 4));
        currentSquare = squares.get(0);
        score = 0;
        text.setTextSize(w / 6);
        new MyTimer(1000L, 10).start();
    }
    class MyTimer extends CountDownTimer {
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            k++;
            for (Square s : squares) {
                s.y += V;
            }
            if (currentSquare.y > getHeight()) {
                state = State.LOSE;
            }
            if (k >= 50) {
                int x = (int) (Math.random() * getWidth());
                int y = (int) (Math.random() * getHeight());
                squares.add(new Square(x, y, x + getWidth() / 4, y + getHeight() / 4));
                k = 0;
            }
        }

        @Override
        public void onFinish() {
            V += 10;
            new MyTimer(1000L, 10).start();
        }

    }
}
