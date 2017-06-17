package com.example.user.miniteamsminigames;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Square {
    Paint paint = new Paint();
    public int x, y, w, h;

    public Square(int x, int y, int w, int h, Paint paint) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.paint = paint;
    }
    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + w, y + h, paint);
    }

}
