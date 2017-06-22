package com.example.user.miniteamsminigames;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.media.MediaPlayer;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;
import static android.graphics.Color.BLACK;
import static com.example.user.miniteamsminigames.MainActivity.slide_pref;
import static com.example.user.miniteamsminigames.SliderAct.lose_music;
import static com.example.user.miniteamsminigames.SliderAct.phon;
import static com.example.user.miniteamsminigames.MainActivity.tf;


public class SliderView extends View {
    public SharedPreferences.Editor slide_edit;
    private static final Paint view = new Paint();
    private static final Paint Timer = new Paint();
    private static final Paint text = new Paint();
    private static final Paint scorecolor = new Paint();
    boolean flag1 = true;
    public int w, h;
    float timer = 0;
    public static State state;
    int score = 0;
    boolean rec = false;
    float clock = 200;
    boolean flag = false;
    Paint col = new Paint();
    Paint colbg = new Paint();
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
        states.add(State.NS_D);
        states.add(State.NS_U);
        states.add(State.NS_L);
        states.add(State.NS_R);
        states.add(State.N);
        states.add(State.NN);
        slide_edit = getContext().getSharedPreferences("slide", MODE_PRIVATE).edit();
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
        if (state == State.LOSE && !rec) {
            if (score > slide_pref.getInt("slide", 0)) {
                slide_edit.putInt("slide", score);
                slide_edit.commit();
            }
            rec = true;
        }
        text.setTypeface(tf);
        //canvas.drawText("" + timer / 10, w / 2, h / 8, Timer);
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
        if (state == State.NS_D) {
            canvas.drawText("Not Down", w / 2, h / 2, text);
        }
        if (state == State.NS_U) {
            canvas.drawText("Not Up", w / 2, h / 2, text);
        }
        if (state == State.NS_L) {
            canvas.drawText("Not Left", w / 2, h / 2, text);
        }
        if (state == State.NS_R) {
            canvas.drawText("Not Right", w / 2, h / 2, text);
        }
        if (state == State.N) {
            canvas.drawText("Nothing", w / 2, h / 2, text);
        }
        if (state == State.NN) {
            canvas.drawText("Not Nothing", w / 2, h / 2, text);
        }
        if (state == State.SPECIAL) {
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
            if (state == State.N) {
                state = states.get((int) (Math.random() * 10));
                flag = true;
                score++;
            } else {
                state = State.LOSE;
            }
        }
        if (timer == 0 && state != State.LOSE && flag) {
            flag = false;
            if (clock > 75) {
                clock--;
            }
            timer = clock;
        }
        if (state == State.LOSE || state == State.PAUSED) {
            SliderAct.pause.setVisibility(INVISIBLE);
        } else
            SliderAct.pause.setVisibility(VISIBLE);
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        Integer evaluatedColor = (Integer) argbEvaluator.evaluate(1 - timer / clock, Color.GREEN, Color.RED);
        col.setColor(evaluatedColor);
        colbg.setColor(Color.BLACK);
        if (state != State.LOSE && !(state == State.PAUSED && SliderAct.st == State.LOSE)) {
            canvas.drawRect(w / 8 - 5, 3 * h / 4 - 5, Math.max(7 * w / 8 - 3 * w / 4 * (1 - timer / clock), w / 8) + 5, 5 * h / 6 + 5, colbg);
        }
        canvas.drawRect(w / 8, 3 * h / 4, Math.max(7 * w / 8 - 3 * w / 4 * (1 - timer / clock), w / 8), 5 * h / 6, col);
        if (state != State.PAUSED) {
            timer--;
        }
        scorecolor.setColor(Color.RED);
        scorecolor.setTextAlign(Paint.Align.CENTER);
        scorecolor.setTypeface(tf);
        if (state != State.LOSE) {
            scorecolor.setTextSize(w / 6);
            canvas.drawText(Integer.toString(score), w / 2, h / 8, scorecolor);
        } else {
            if (flag1)  {
                phon.stop();
                lose_music.setLooping(false);
                lose_music.start();
                phon.stop();
                lose_music.setLooping(false);
                if (!lose_music.isPlaying()) {
                    try {
                        lose_music.prepareAsync();
                        lose_music.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                lose_music.seekTo(0);
                                lose_music.start();
                            }
                        });
                    } catch (IllegalStateException e) {
                        lose_music.seekTo(0);
                        lose_music.start();
                    }
                } else {
                    lose_music.stop();
                    lose_music.reset();
                    try {
                        lose_music.prepareAsync();
                        lose_music.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                lose_music.seekTo(0);
                                lose_music.start();
                            }
                        });
                    } catch (IllegalStateException e) {
                        lose_music.seekTo(0);
                        lose_music.start();
                    }
                }
                flag1 = false;
            }
            scorecolor.setTextSize(w / 3);
            canvas.drawText(Integer.toString(score), w / 2, h / 2, scorecolor);
        }
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
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
                    if (state == State.S_L || state == State.S_D || state == State.S_U || state == State.NS_R || state == State.N) {
                        state = State.LOSE;
                        //text.setColor(Color.GREEN);
                    } else if (state != State.LOSE) {
                        state = states.get((int) (Math.random() * 10));
                        score++;
                    }
                    //    while (state == State.S_R)
                    //        state = states.get((int) (Math.random() * 8));
                    //}

                } else if (-deltaX > MIN_DISTANCE && Math.abs(deltaX) > Math.abs(deltaY)) {
                    timer = 0;
                    flag = true;
                    Log.d("direction", "LEFT");
                    if (state == State.S_R || state == State.S_D || state == State.S_U || state == State.NS_L || state == State.N) {
                        state = State.LOSE;
                        //text.setColor(Color.GREEN);
                    } else if (state != State.LOSE) {
                        state = states.get((int) (Math.random() * 10));
                        score++;
                    }
                    //else {
                    //    while (state == State.S_L)
                    //        state = states.get((int) (Math.random() * 8));
                    //}
                } else if (deltaY > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(deltaX)) {
                    timer = 0;
                    flag = true;
                    if (state == State.S_R || state == State.S_L || state == State.S_U || state == State.NS_D || state == State.N) {
                        state = State.LOSE;
                        //text.setColor(Color.GREEN);
                    } else if (state != State.LOSE) {
                        state = states.get((int) (Math.random() * 10));
                        score++;
                    }
                    //else {
                    //    while (state == State.S_D)
                    //        state = states.get((int) (Math.random() * 8));
                    //}
                    Log.d("direction", "DOWN");
                } else if (-deltaY > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(deltaX)) {
                    timer = 0;
                    flag = true;
                    if (state == State.S_R || state == State.S_D || state == State.S_L || state == State.NS_U || state == State.N) {
                        state = State.LOSE;
                        //text.setColor(Color.GREEN);
                    } else if (state != State.LOSE) {
                        state = states.get((int) (Math.random() * 10));
                        score++;
                    }
                    //else {
                    //    while (state == State.S_U)
                    //        state = states.get((int) (Math.random() * 8));
                    //}
                    Log.d("direction", "UP");
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    public void restart() {
        phon.start();
        if (lose_music.isPlaying()) {
            lose_music.stop();
        }
        if (!phon.isPlaying()) {
            try {
                phon.prepareAsync();
                phon.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        phon.seekTo(0);
                        phon.setLooping(true);
                        phon.start();
                    }
                });
            } catch (IllegalStateException e) {
                phon.seekTo(0);
                phon.setLooping(true);
                phon.start();
            }
        } else {
            phon.stop();
            phon.reset();
            try {
                phon.prepareAsync();
                phon.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        phon.seekTo(0);
                        phon.setLooping(true);
                        phon.start();
                    }
                });
            } catch (IllegalStateException e) {
                phon.seekTo(0);
                phon.setLooping(true);
                phon.start();
            }
        }
        flag = true;
        text.setTextSize(w / 6);
        Timer.setTextSize(w / 6);
        state = states.get((int) (Math.random() * 10));
        timer = clock;
    }
}

