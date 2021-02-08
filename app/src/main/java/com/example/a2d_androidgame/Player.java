package com.example.a2d_androidgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.util.DisplayMetrics;

import androidx.core.content.*;

public class Player
{
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private double posX, posY, radius;
    private Paint paint;
    private double vX;
    private double vY;

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

    public void update(Joystick joystick)
    {
        vX = joystick.getActuatorX() * MAX_SPEED;
        vY = joystick.getActuatorY() * MAX_SPEED;
        int maxHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
        int maxWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        posX += vX;
        posY += vY;
        if(posX < radius)
            posX = maxWidth - radius;
        if(posX > maxWidth - radius)
            posX = radius;
        if(posY < radius)
            posY = maxHeight - radius;
        if(posY > maxHeight - radius)
            posY = radius;
    }

    public void setPosition(double x, double y)
    {
        this.posX = x;
        this.posY = y;
    }
}
