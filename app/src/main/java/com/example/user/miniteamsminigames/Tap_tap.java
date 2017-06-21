package com.example.user.miniteamsminigames;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import static com.example.user.miniteamsminigames.PointsActivity.mediaPlayer;
import static com.example.user.miniteamsminigames.PointsActivity.music_in_game;
import static com.example.user.miniteamsminigames.PointsActivity.tf;
import static com.example.user.miniteamsminigames.TapActivity.loser;
import static com.example.user.miniteamsminigames.TapActivity.tap_music;

/**
 * Created by User on 17.06.2017.
 */

public class Tap_tap extends View{
    public ArrayList<Square> squares = new ArrayList<>();
    State state = State.NOT_LOSE;
    boolean flag = true;
    private static final Paint bg = new Paint();
    private static final Paint bg2 = new Paint();

    private static final Paint red = new Paint();
    int vx, vy, r;
    int w, h, d;
    Tap_Player player;
    double V = 6;
    int point = 0;
    double score = 0;
    double dt = 0.01;
    double cnt = 0;
    boolean ans = false;
    boolean lr = false; // left - false, right - true

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
        bg.setColor(Color.BLACK);
        bg2.setColor(Color.WHITE);
        red.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = getMeasuredWidth();
        h = getMeasuredHeight();
        d = getMeasuredWidth() / 6;
        point = d / 10;
        if (!ans) {
            restart();
            ans = true;
        }
    }

    private void restart() {
        int random = (int) (Math.random() * w + w / 4);
//        int random = 200;
        Log.d("KEK111", Integer.toString(random));
        squares.add(new Square(- d / 2, - random + d / 2, d, random, bg2, true));
        for (int i = 0; i < 20; i++) {
            random = (int) (Math.random() * w / 2 + w / 2);
//            random = 200;
            Log.d("KEK2", Integer.toString(random));
            Square s = squares.get(squares.size() - 1);
            if (s.dir) {
                squares.add(new Square(s.x - random + d, s.y, random, d, bg2, !s.dir));
            } else {
                squares.add(new Square(s.x, s.y - random + d, d, random, bg2, !s.dir));
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, w, h, bg);
        canvas.translate(w / 2, 3 * h / 4);
        canvas.rotate(45);
        boolean kek = false;
        for (Square s : squares) {
            if ((s.x + point) * (s.x + s.w + point) < 0 && (s.y + point) * (s.y + s.h + point) < 0 &&
                    (s.x - point) * (s.x + s.w - point) < 0 && (s.y - point) * (s.y + s.h - point) < 0) {
                kek = true;
            }
        }
        if (!kek) {
            state = State.LOSE;
        }
        for (Square sq : squares) {
            sq.draw(canvas);
        }
        if (!lr) {
            for (Square sq : squares) {
                sq.y += V;
            }
        } else {
            for (Square sq : squares) {
                sq.x += V;
            }
        }
        canvas.drawRect(-point, -point, 2 * point, 2 * point, bg);
        Square firstsquare = squares.get(0);
        if (firstsquare.y > h / 2) {
            squares.remove(0);
            int random = (int) (Math.random() * w / 2 + w / 2);
            Log.d("KEK2", Integer.toString(random));
            Square s = squares.get(squares.size() - 1);
            if (s.dir) {
                squares.add(new Square(s.x - random + d, s.y, random, d, bg2, !s.dir));
            } else {
                squares.add(new Square(s.x, s.y - random + d, d, random, bg2, !s.dir));
            }
        }

        if (state == State.LOSE) {
            V = 0;
        }

        if (state == State.NOT_LOSE) {
            score += dt;
            if (cnt > 10) {
                V += 1;
                cnt = 0;
                Log.d("LOL", "lol");
            } else {
                cnt += dt;
            }
        }
        canvas.rotate(-45);
        canvas.translate(- w / 2, - 3 * h / 4);
        Paint text = new Paint();
        text.setColor(Color.RED);
        text.setTextAlign(Paint.Align.CENTER);
        text.setTextSize(w / 6);
        text.setTypeface(tf);
        if (state == State.NOT_LOSE) {
            if ((int) (score * 100) % 100 >= 10) {
                canvas.drawText((int) (score * 100) / 100 + "." + (int) (score * 100) % 100, w / 2, h / 8, text);
            } else {
                canvas.drawText((int) (score * 100) / 100 + ".0" + (int) (score * 100) % 100, w / 2, h / 8, text);

            }
        } else {
            if (flag)  {
                tap_music.stop();
                loser.setLooping(false);
                loser.start();
                flag = false;
            }
            text.setTextSize(w / 3);
            if ((int) (score * 100) % 100 >= 10) {
                canvas.drawText((int) (score * 100) / 100 + "." + (int) (score * 100) % 100, w / 2, h / 2, text);
            } else {
                canvas.drawText((int) (score * 100) / 100 + ".0" + (int) (score * 100) % 100, w / 2, h / 2, text);

            }
        }
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            lr = !lr;
        }
        return true;
    }
}
