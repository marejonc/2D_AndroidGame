package com.example.a2d_androidgame;

import android.graphics.*;

class Joystick
{
    private int outerCircleCenterPositionX;
    private int outerCircleCenterPositionY;
    private int innerCircleCenterPositionX;
    private int innerCircleCenterPositionY;
    private int outerCircleRadius;
    private int innerCircleRadius;

    private double actuatorX;
    private double actuatorY;
    private boolean isPressed;

    private Paint outerCirclePaint;
    private Paint innerCirclePaint;

    public Joystick(int centerPosX, int centerPosY, int outerCircleRadius, int innerCircleRadius)
    {
        outerCircleCenterPositionX = centerPosX;
        outerCircleCenterPositionY = centerPosY;
        innerCircleCenterPositionX = centerPosX;
        innerCircleCenterPositionY = centerPosY;

        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(Color.DKGRAY);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawCircle(outerCircleCenterPositionX, outerCircleCenterPositionY, outerCircleRadius, outerCirclePaint);
        canvas.drawCircle(innerCircleCenterPositionX, innerCircleCenterPositionY, innerCircleRadius, innerCirclePaint);
    }

    public void update() { updateInnerCirclePosition(); }

    private void updateInnerCirclePosition()
    {
        innerCircleCenterPositionX = (int)(outerCircleCenterPositionX + actuatorX * outerCircleRadius);
        innerCircleCenterPositionY = (int)(outerCircleCenterPositionY + actuatorY * outerCircleRadius);
    }

    public double calculateDistance(double x, double y) { return Math.sqrt(x * x + y * y); }

    public boolean isPressed(double x, double y)
    { return calculateDistance(outerCircleCenterPositionX - x, outerCircleCenterPositionY - y) < outerCircleRadius; }

    public void setIsPressed(boolean isPressed) { this.isPressed = isPressed; }

    public boolean getIsPressed() { return isPressed; }

    public void setActuator(double x, double y)
    {
        double dX = x - outerCircleCenterPositionX;
        double dY = y - outerCircleCenterPositionY;
        double dDistance = calculateDistance(x - outerCircleCenterPositionX, y - outerCircleCenterPositionY);

        if(dDistance < outerCircleRadius)
        {
            actuatorX = dX / outerCircleRadius;
            actuatorY = dY / outerCircleRadius;
        }
        else
        {
            actuatorX = dX / dDistance;
            actuatorY = dY / dDistance;
        }

    }

    public void resetActuator() { actuatorX = 0.0; actuatorY = 0.0; }

    public double getActuatorX() { return actuatorX; }

    public double getActuatorY() { return actuatorY; }
}
