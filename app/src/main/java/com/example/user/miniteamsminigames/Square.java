package com.example.user.miniteamsminigames;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Square {
    Paint paint = new Paint();
    public int x, y, w, h;
    public boolean dir;

    public Square(int x, int y, int w, int h, Paint paint, boolean dir) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.paint = paint;
        this.dir = dir;
    }
    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + w, y + h, paint);
    }

}
