package com.example.a2d_androidgame;

import android.graphics.*;

import androidx.core.content.ContextCompat;

abstract class Circle extends GameObject
{
    private double radius;
    private Paint paint;

    public Circle(int color, double posX, double posY, double radius)
    {
        super(posX, posY);
        this.radius = radius;

        paint = new Paint();
        paint.setColor(color);
    }

    public void draw(Canvas canvas) { canvas.drawCircle((float)posX, (float)posY, (float)radius, paint); }
}
