package com.example.a2d_androidgame;

import android.content.Context;
import android.graphics.*;
import androidx.core.content.*;

public class Player
{
    private double posX, posY, radius;
    private Paint paint;

    public Player(Context context, double posX, double posY, double radius)
    {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawCircle((float)posX, (float)posY, (float)radius, paint);
    }

    public void update()
    {

    }

    public void setPosition(double x, double y)
    {
        this.posX = x;
        this.posY = y;
    }
}
