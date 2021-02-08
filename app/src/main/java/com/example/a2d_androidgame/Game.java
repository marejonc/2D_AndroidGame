package com.example.a2d_androidgame;

import android.content.*;
import android.graphics.*;
import android.view.*;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class Game extends SurfaceView implements SurfaceHolder.Callback
{
    private final Player player;
    private final Joystick joystick;
    private GameLoop gameLoop;

    public Game(Context context)
    {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        player = new Player(getContext(), 960, 540, 30);
        joystick = new Joystick(250, 830, 90, 55);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if(joystick.isPressed((double)event.getX(), (double)event.getY()))
                {
                    joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed())
                {
                    joystick.setActuator((double)event.getX(), (double)event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
                joystick.setIsPressed(false);
                joystick.resetActuator();
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) { gameLoop.startLoop(); }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {}

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);
        player.draw(canvas);
        joystick.draw(canvas);
    }

    public void drawUPS(Canvas canvas)
    {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + averageUPS, 100, 100, paint);
    }

    public void drawFPS(Canvas canvas)
    {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.purple_200);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + averageFPS, 100, 200, paint);
    }

    public void update()
    {
        player.update(joystick);
        joystick.update();
    }
}
