package com.example.user.miniteamsminigames;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Square {
    private static final Paint paint = new Paint();
    public int x, y, w, h;

    public Square(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        paint.setColor(Color.BLACK);
    }
    public void draw(Canvas canvas) {
        canvas.drawRect(x, y, x + w, y + h, paint);
    }

}
