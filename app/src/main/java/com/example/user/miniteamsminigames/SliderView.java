package com.example.user.miniteamsminigames;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import static android.graphics.Color.BLACK;


public class SliderView extends View {

    private static final Paint view = new Paint();
    private static final Paint Timer = new Paint();
    private static final Paint text = new Paint();
    public int w, h;
    float timer = 0;
    int deltaw = 0;
    int x = 65280;
    State state;
    float clock = 150;
    boolean flag = false;
    Paint col = new Paint();
    ArrayList<State> states = new ArrayList<>();
    private float x1, x2, y1, y2;
    static final int MIN_DISTANCE = 150;

    public SliderView(Context context) {
        super(context);
        init();
    }

    public SliderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SliderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SliderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        text.setColor(BLACK);
        text.setTextAlign(Paint.Align.CENTER);
        Timer.setColor(Color.RED);
        Timer.setTextAlign(Paint.Align.CENTER);
        states.add(State.S_D);
        states.add(State.S_U);
        states.add(State.S_L);
        states.add(State.S_R);
        restart();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = getMeasuredWidth();
        h = getMeasuredHeight();
        restart();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("" + timer, w / 2, h / 8, Timer);
        canvas.drawText(" " + (150 - clock), w / 2, 7 * h / 8, Timer);
        if (state == State.S_D) {
            canvas.drawText("Down", w / 2, h / 2, text);
        }
        if (state == State.S_U) {
            canvas.drawText("Up", w / 2, h / 2, text);
        }
        if (state == State.S_L) {
            canvas.drawText("Left", w / 2, h / 2, text);
        }
        if (state == State.S_R) {
            canvas.drawText("Right", w / 2, h / 2, text);
        }
        if (state == State.LOSE) {
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            view.setColor(color);
            canvas.drawRect(0, 0, w, h, view);
            for (int i = 0; i < 100; i++) {
                int q1 = (int) (Math.random() * w);
                int q2 = (int) (Math.random() * w);
                int p1 = (int) (Math.random() * h);
                int p2 = (int) (Math.random() * h);
                rnd = new Random();
                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                view.setColor(color);
                canvas.rotate((int) (Math.random() * 180));
                canvas.drawRect(Math.min(q1, q2), Math.min(p1, p2), Math.max(q1, q2), Math.max(p1, p2), view);
            }
            postInvalidateDelayed(1);
        }
            if (timer == 0 && !flag) {
                state = State.LOSE;
            }

            if (timer == 0 && state != State.LOSE && flag) {
                flag = false;
                if (clock > 75) {
                    clock--;
                }
                timer = clock;
            }

        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        Integer evaluatedColor = (Integer) argbEvaluator.evaluate(1 - timer / clock, Color.GREEN, Color.RED);
        col.setColor(evaluatedColor);
        canvas.drawRect(w / 8, h / 6, Math.max(7 * w / 8 - 3 * w / 4 * (1 - timer / clock), w / 8), h / 3, col);
        timer--;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = 65280;
        deltaw = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                Log.d("direction", "tknul");
                return true;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();
                Log.d("direction", "vыtknul");
                float deltaX = x2 - x1;
                float deltaY = y2 - y1;
                if (deltaX > MIN_DISTANCE && Math.abs(deltaX) > Math.abs(deltaY)) {
                    Log.d("direction", "RIGHT");
                    timer = 0;
                    flag = true;
                    if (state != State.S_R) {
                        state = State.LOSE;
                        //text.setColor(Color.GREEN);
                    }
                    else {
                        while (state == State.S_R)
                            state = states.get((int) (Math.random() * 4));
                    }

                } else if (-deltaX > MIN_DISTANCE && Math.abs(deltaX) > Math.abs(deltaY)) {
                    timer = 0;
                    flag = true;
                    Log.d("direction", "LEFT");
                    if (state != State.S_L) {
                        state = State.LOSE;
                        //text.setColor(Color.GREEN);
                    }
                    else {
                        while (state == State.S_L)
                            state = states.get((int) (Math.random() * 4));
                    }
                } else if (deltaY > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(deltaX)) {
                    timer = 0;
                    flag = true;
                    if (state != State.S_D) {
                        state = State.LOSE;
                        //text.setColor(Color.GREEN);
                    }
                    else {
                        while (state == State.S_D)
                            state = states.get((int) (Math.random() * 4));
                    }
                    Log.d("direction", "DOWN");
                } else if (-deltaY > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(deltaX)) {
                    timer = 0;
                    flag = true;
                    if (state != State.S_U) {
                        state = State.LOSE;
                        //text.setColor(Color.GREEN);
                    }
                    else {
                        while (state == State.S_U)
                            state = states.get((int) (Math.random() * 4));
                    }
                    Log.d("direction", "UP");
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void restart() {
        text.setTextSize(w / 6);
        Timer.setTextSize(w / 6);
        state = states.get((int) (Math.random() * 4));
        timer = clock;
    }
}

