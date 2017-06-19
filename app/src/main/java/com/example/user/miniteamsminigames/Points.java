package com.example.user.miniteamsminigames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class Points extends View {
    private static final Paint bg = new Paint();
    private static final Paint black = new Paint();
    private static final Paint white = new Paint();
    private ArrayList<Square> squares;
    private ArrayList<Square> whiteSquares;
    public double V = 10;
    public int score = 0;
    public State state = State.NOT_LOSE;

    public Points(Context context) {
        super(context);
        bg.setColor(Color.YELLOW);
        //bg.setColor(Color.WHITE);
        black.setColor(Color.BLACK);
        white.setColor(Color.WHITE);
        squares = new ArrayList();
        whiteSquares = new ArrayList();
        restart(getWidth(), getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Square ss = new Square(0, 0, getWidth(), getHeight(), bg);
        ss.draw(canvas);
        if (whiteSquares.get(0).y > getHeight()) {
            for (int i = 0; i < 4; i++) {
                whiteSquares.add(new Square(i * getWidth() / 4, -getHeight() / 4, getWidth() / 4, getHeight() / 4, white));
                whiteSquares.remove(0);
            }
        }
        if (squares.get(squares.size() - 1).y >= 0) {
            int random = (int) (Math.random() * 4);
            squares.add(new Square(random * getWidth() / 4, -getHeight() / 4, getWidth() / 4, getHeight() / 4, black));
        }
        for (Square s : squares) {
            s.draw(canvas);
        }
        for (Square s : whiteSquares) {
            s.draw(canvas);
        }
        for (Square s : whiteSquares) {
            s.y += V;
        }
        for (Square s : squares) {
            s.y += V;
        }
        if (squares.get(0).y + squares.get(0).h > getHeight()) {
            state = State.LOSE;
            V = 0;
        }
        if (state == State.LOSE) {
            Paint text = new Paint();
            text.setColor(Color.BLUE);
            text.setTextAlign(Paint.Align.CENTER);
            text.setTextSize(getWidth() / 6);
            canvas.drawText("Score: " + score, getWidth() / 2, getHeight() / 2, text);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (state != State.LOSE) {
                if (event.getX() > squares.get(0).x && event.getX() < squares.get(0).x + squares.get(0).w
                        && event.getY() > squares.get(0).y && event.getY() < squares.get(0).y + squares.get(0).h) {
                    score++;
                    if (squares.size() != 1) {
                        squares.remove(0);
                    }
                } else {
                    state = State.LOSE;
                    V = 0;
                }
            }
        }
        return true;
    }

    public void restart(int width, int height) {
        int random = (int) (Math.random() * 4);
        squares.add(new Square(random * width / 4, -height / 4, width / 4, height / 4, black));
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                whiteSquares.add(new Square(width / 4 * i, height / 4 * j, width / 4, height / 4, white));
                Log.d("KEK2", Integer.toString(width) + " " + Integer.toString(height));
                Log.d("KOK", Integer.toString(getWidth() / 4 * i) + " " + Integer.toString(getHeight() / 4 * j));
            }
        }
        score = 0;
        new MyTimer(1000L, 10).start();
    }

    class MyTimer extends CountDownTimer {
        public MyTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }

        @Override
        public void onFinish() {
            if (state != State.LOSE) {
                V += 1;
                new MyTimer(1000L, 10).start();
            }
        }

    }
}