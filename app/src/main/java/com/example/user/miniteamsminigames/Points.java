package com.example.user.miniteamsminigames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class Points extends View {
    private static final Paint bg = new Paint();
    private static final Paint bg1 = new Paint();
    private static final Paint black = new Paint();
    private static final Paint white = new Paint();
    private ArrayList<Square> squares;
    private ArrayList<Square> whiteSquares;
    public double V = 10;
    public int score = 0;
    public int w, h;
    public State state = State.NOT_LOSE;
    boolean ans = false;

    public Points(Context context) {
        super(context);
        init();
    }

    public Points(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Points(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Points(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        bg.setColor(Color.WHITE);
        bg1.setColor(Color.BLACK);
        black.setColor(Color.WHITE);
        white.setColor(Color.BLACK);
        squares = new ArrayList<>();
        whiteSquares = new ArrayList<>();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        w = getMeasuredWidth();
        h = getMeasuredHeight();
        if (!ans) {
            restart();
            ans = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Square ss = new Square(0, 0, w, h, bg1, false);
        ss.draw(canvas);
        ss = new Square(0, 0, w - 1, h, bg, false);
        ss.draw(canvas);
        int diff = whiteSquares.get(0).y - h;
        if (diff >= 0) {
            for (int i = 0; i < 4; i++) {
                whiteSquares.add(new Square(i * w / 4, 2 + diff + (-h / 4), w / 4 - 1, h / 4 - 1, white, false));
                whiteSquares.remove(0);
            }
        }
        diff = squares.get(squares.size() - 1).y;
        if (diff > 0) {
            int random = (int) (Math.random() * 4);
            squares.add(new Square(random * w / 4, diff - h / 4, w / 4 - 1, h / 4 - 1, black, false));
        }
        for (Square s : whiteSquares) {
            s.draw(canvas);
        }
        for (Square s : squares) {
            s.draw(canvas);
        }
        for (Square s : whiteSquares) {
            s.y += V;
        }
        for (Square s : squares) {
            s.y += V;
        }
        if (squares.get(0).y > h) {
            state = State.LOSE;
            V = 0;
        }
        if (state == State.LOSE) {
            Paint text = new Paint();
            text.setColor(Color.BLUE);
            text.setTextAlign(Paint.Align.CENTER);
            text.setTextSize(w / 6);
            canvas.drawText("Score: " + score, w / 2, h / 2, text);
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

    public void restart() {
        int random = (int) (Math.random() * 4);
        squares.add(new Square(random * w / 4, -h / 4, w / 4 - 1, h / 4 - 1, black, false));
        for (int i = 3; i >= -1; i--) {
            for (int j = 3; j >= 0; j--) {
                whiteSquares.add(new Square(w / 4 * j, h / 4 * i, w / 4 - 1, h / 4 - 1, white, false));
            }
        }
        score = 0;
        new MyTimer(3000L, 10).start();
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
                new MyTimer(3000L, 10).start();
            }
        }

    }
}