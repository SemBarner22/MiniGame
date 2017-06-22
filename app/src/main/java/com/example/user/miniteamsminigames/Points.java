package com.example.user.miniteamsminigames;

import android.content.Context;
import android.content.SharedPreferences;
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

import static android.content.Context.MODE_PRIVATE;
import static com.example.user.miniteamsminigames.MainActivity.piano_pref;
import static com.example.user.miniteamsminigames.MainActivity.tf;
import static com.example.user.miniteamsminigames.PointsActivity.mediaPlayer;
import static com.example.user.miniteamsminigames.PointsActivity.tv;
import static com.example.user.miniteamsminigames.PointsActivity.music_in_game;

public class Points extends View {
    public SharedPreferences.Editor piano_edit;
    private static final Paint bg = new Paint();
    private static final Paint bg1 = new Paint();
    private static final Paint black = new Paint();
    private static final Paint white = new Paint();
    boolean flag = true;
    private ArrayList<Square> squares;
    private ArrayList<Square> squares2;
    private ArrayList<Square> whiteSquares;
    public static double V;
    public int score = 0;
    public int w, h;
    public static int k;
    boolean rec = false;
    public State state = State.NOT_LOSE;
    boolean ans = false;
    int intans = 0;
    boolean time = false;

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
        bg.setColor(Color.BLACK);
        bg1.setColor(Color.BLACK);
        black.setColor(Color.BLACK);
        white.setColor(Color.WHITE);
        squares = new ArrayList<>();
        squares2 = new ArrayList<>();
        whiteSquares = new ArrayList<>();
        piano_edit = getContext().getSharedPreferences("piano", MODE_PRIVATE).edit();
        V = 10;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        w = getMeasuredWidth();
        h = getMeasuredHeight();
//        if (!ans) {
//            restart();
//            ans = true;
//        }
        intans++;
        if (intans == 2) {
            restart();
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
            squares2.add(new Square(random * w / 4, diff - h / 4, w / 4 - 1, h / 4, white, false));
        }
        for (Square s : whiteSquares) {
            s.draw(canvas);
        }
        for (Square s : squares2) {
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
        for (Square s : squares2) {
            s.y += V;
        }
        if (squares.get(0).y > h) {
            state = State.LOSE;
            V = 0;
        }
        Paint text = new Paint();
        text.setColor(Color.RED);
        text.setTextAlign(Paint.Align.CENTER);
        text.setTypeface(tf);
        if (state == State.NOT_LOSE) {
            PointsActivity.pause.setVisibility(VISIBLE);
            text.setTextSize(w / 6);
            // шрифтик
           // PointsActivity.tv.setVisibility(View.VISIBLE);
           // PointsActivity.tv.setText(" " + score);
            canvas.drawText(Integer.toString(score), w / 2, h / 8, text);
        } else {
            V = 0;
            if (flag)  {
                music_in_game.stop();
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
                flag = false;
             }
             if (!rec) {
                 if (score > piano_pref.getInt("piano", 0)) {
                     piano_edit.putInt("piano", score);
                     piano_edit.commit();
                 }
                 rec = true;
             }
            PointsActivity.pause.setVisibility(INVISIBLE);
            PointsActivity.tv.setVisibility(View.VISIBLE);
            text.setTextSize(w / 3);
            canvas.drawText(Integer.toString(score), w / 2, h / 2, text);

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
                        squares2.remove(0);
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
        if (time == false) {
            time = true;
            new MyTimer(3000L, 10).start();
        }
//        new MyTimer(3000L, 10).start();
        rec = false;
        flag = true;
        music_in_game.start();
        state = State.NOT_LOSE;
        tv.setVisibility(INVISIBLE);
        bg.setColor(Color.BLACK);
        bg1.setColor(Color.BLACK);
        black.setColor(Color.BLACK);
        white.setColor(Color.WHITE);
        squares = new ArrayList<>();
        squares2 = new ArrayList<>();
        whiteSquares = new ArrayList<>();
        int random = (int) (Math.random() * 4);
        squares.add(new Square(random * w / 4, -h / 4, w / 4 - 1, h / 4 - 1, black, false));
        for (int i = 4; i >= -1; i--) {
            for (int j = 3; j >= 0; j--) {
                whiteSquares.add(new Square(w / 4 * j, h / 4 * i, w / 4 - 1, h / 4 - 1, white, false));
            }
        }
        score = 0;
        k = 1;
        V = 10;
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
            if (true) {
                V += k;
                new MyTimer(3000L, 10).start();
            }
        }

    }
}