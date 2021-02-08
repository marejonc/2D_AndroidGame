package com.example.a2d_androidgame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import androidx.core.content.*;

class Player extends Circle
{
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;

    public Player(Context context, Joystick joystick, double posX, double posY, double radius)
    {
        super(ContextCompat.getColor(context, R.color.player), posX, posY, radius);
        this.joystick = joystick;
    }

    public void update()
    {
        vX = joystick.getActuatorX() * MAX_SPEED;
        vY = joystick.getActuatorY() * MAX_SPEED;
        posX += vX;
        posY += vY;
    }
}
