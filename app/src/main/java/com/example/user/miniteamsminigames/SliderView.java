package com.example.user.miniteamsminigames;

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


public class SliderView extends View{

    private static final ArrayList<Paint> bg = new ArrayList<>(4);
    private static final Paint view = new Paint();
    public int w, h;
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
        Paint bluePaint = new Paint();
        bluePaint.setColor(Color.BLUE);
        bg.add(bluePaint);
        Paint greenPaint = new Paint();
        greenPaint.setColor(Color.GREEN);
        bg.add(greenPaint);
        Paint redPaint = new Paint();
        redPaint.setColor(Color.RED);
        bg.add(redPaint);
        Paint yellowPaint = new Paint();
        yellowPaint.setColor(Color.YELLOW);
        bg.add(yellowPaint);
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
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        view.setColor(color);
        canvas.drawRect(0, 0, w, h, view);
        for (int i = 0; i < 1000; i++) {
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
        postInvalidateDelayed(1000);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
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
                if (deltaX > MIN_DISTANCE && Math.abs(deltaX) > Math.abs(deltaY))
                {
                    Log.d("direction", "RIGHT");
                }
                else if (-deltaX > MIN_DISTANCE && Math.abs(deltaX) > Math.abs(deltaY))
                {
                    Log.d("direction", "LEFT");
                }
                else if (deltaY > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(deltaX))
                {
                    Log.d("direction", "DOWN");
                }
                else if (-deltaY > MIN_DISTANCE && Math.abs(deltaY) > Math.abs(deltaX))
                {
                    Log.d("direction", "UP");
                }
                return true;
        }
        return super.onTouchEvent(event);
    }
    public void restart()
    {

    }
}
