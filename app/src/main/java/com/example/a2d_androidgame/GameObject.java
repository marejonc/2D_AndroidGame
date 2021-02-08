package com.example.a2d_androidgame;

import android.graphics.*;

abstract class GameObject
{
    protected double posX, posY;
    protected double vX, vY;

    public GameObject(double posX, double posY)
    {
        this.posX = posX;
        this.posY = posY;
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();
}
