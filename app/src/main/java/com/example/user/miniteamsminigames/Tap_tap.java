package com.example.user.miniteamsminigames;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by User on 17.06.2017.
 */

public class Tap_tap extends View{
    public ArrayList<Square> squares = new ArrayList<>();
    State state = State.NOT_LOSE;
    private static final Paint bg = new Paint();
    int vx, vy, r;
    int w, h, d;
    Tap_Player player;
    int V = 10;

    public Tap_tap(Context context) {
        super(context);
        init();
    }

    public Tap_tap(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Tap_tap(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public Tap_tap(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        bg.setColor(Color.GREEN);
        restart();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = getMeasuredWidth();
        h = getMeasuredHeight();
        d = getMeasuredWidth() / 8;
        restart();
    }

    private void restart() {
        for (int i = 0; i < 5; i++) {
            squares.add(new Square(0, 0, 0, 0, bg, i % 2 == 1));
        }
        int random = (int) (Math.random() * w / 4 + w / 2);
        squares.add(new Square(- d / 2, random - d / 2, d, random, bg, true));
        for (int i = 0; i < 20; i++) {
            random = (int) (Math.random() * w / 4 + w / 2);
            squares.add(new Square(squares.get(squares.size() - 1).x + random - d, squares.get(squares.size() - 1).y, random, d, bg, !squares.get(squares.size() - 1).dir));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(w / 2, h / 4);
        canvas.rotate(45);
        for (Square sq : squares) {
            sq.draw(canvas);
        }
        if (squares.get(5).dir) { //true - vertical, false - horizontal
            for (Square sq : squares) {
                sq.y += V;
            }
        } else {
            for (Square sq : squares) {
                sq.x += V;
            }
        }
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && state != State.LOSE) {
            player.vx = - player.vx;
        }
        return true;
    }
}
