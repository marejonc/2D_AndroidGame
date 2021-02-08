package com.example.a2d_androidgame;

import android.graphics.*;
import android.view.*;

class GameLoop extends Thread
{
    private boolean isRunning = false;
    private Game game;
    private SurfaceHolder surfaceHolder;
    private double averageUPS;
    private double averageFPS;
    public static final double MAX_UPS = 60.0;
    private static final double UPS_PERIOD = 1000 / MAX_UPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder)
    {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    public double getAverageUPS() { return averageUPS; }

    public double getAverageFPS() { return averageFPS; }

    public void startLoop()
    {
        isRunning = true;
        start();
    }

    @Override
    public void run()
    {
        super.run();

        int updateCount = 0, frameCount = 0;
        long startTime, elapsedTime, sleepTime;

        Canvas canvas = null;
        startTime = System.currentTimeMillis();
        while(isRunning)
        {
            try
            {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder)
                {
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                }
            }
            catch(IllegalArgumentException e) { e.printStackTrace(); }
            finally
            {
                if(canvas != null)
                {
                    try
                    {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }
                    catch(IllegalArgumentException e) { e.printStackTrace(); }
                }
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
            if(sleepTime > 0)
            {
                try{ sleep(sleepTime); }
                catch (InterruptedException e) { e.printStackTrace(); }
            }

            while(sleepTime < 0 && updateCount < MAX_UPS - 1)
            {
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long)(updateCount * UPS_PERIOD - elapsedTime);
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if(elapsedTime >= 1000)
            {
                averageUPS = updateCount / (elapsedTime / 1000.0);
                averageFPS = updateCount / (elapsedTime / 1000.0);
                updateCount = 0; frameCount = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
}
