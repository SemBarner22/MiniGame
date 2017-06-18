package com.example.user.miniteamsminigames;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by User on 17.06.2017.
 */

public class Tap_tap extends View{
    public ArrayList<Point> upperPoints = new ArrayList<>();
    public ArrayList<Point> downPoints = new ArrayList<>();
    public int distance = 50;
    State state = State.NOT_LOSE;
    private static final Paint bg = new Paint();
    private static final Paint line = new Paint();
    int vx, vy, r;
    Tap_Player player;

    public Tap_tap(Context context) {
        super(context);
        bg.setColor(Color.WHITE);
        line.setColor(Color.BLACK);
        restart();
    }

    private void restart() {
        upperPoints.add(new Point(getWidth() / 2, 4 * getHeight() / 6));
        downPoints.add(new Point(getWidth() / 2, 4 * getHeight() / 6 + distance));
        vx = 10;
        vy = 10;
        r = 10;
        player = new Tap_Player(getWidth() / 2, 5 * getHeight() / 6, vx, vy, r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 1; i < upperPoints.size(); i++) {
            canvas.drawLine(upperPoints.get(i).x, upperPoints.get(i).y, upperPoints.get(i - 1).x, upperPoints.get(i - 1).y, line);
            canvas.drawLine(downPoints.get(i).x, upperPoints.get(i).y, upperPoints.get(i - 1).x, upperPoints.get(i - 1).y, line);
        }
        player.draw(canvas);
        invalidate();
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && state != State.LOSE) {
            player.vy = - player.vy;
        }
        return true;
    }
}
