package com.example.user.miniteamsminigames;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by User on 18.06.2017.
 */

public class Tap_Player {
    Paint paint = new Paint();
    public int x, y, vx, vy, r;

    public Tap_Player(int x, int y, int vx, int vy, int r) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.r = r;
        paint.setColor(Color.RED);
    }
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, r, paint);
    }


}
