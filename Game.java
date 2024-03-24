package com.example.playactivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

/*
 * Maison Gulyas: All My Updated Code from 1 - 5, working on rest rn.
 * Manages all objects in game
 * Handles game updates and rendering
 */
public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Joystick joystick;
    private GameLoop gameLoop;
   private final Player player;
    public Game(Context context) {
        super(context);

        //Get Surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);


        gameLoop = new GameLoop(this, surfaceHolder);

        //initialize player & game objects
        joystick = new Joystick(275, 700, 70, 40);
        player = new Player(getContext(),2 * 500, 500, 30);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      switch (event.getAction()){
          case MotionEvent.ACTION_DOWN:
              if(joystick.isPressed((double) event.getX(), (double) event.getY())) {
                  joystick.setIsPressed(true);
              }
              return true;
          case MotionEvent.ACTION_MOVE:
              if (joystick.getIsPressed()) {
                  joystick.setActuator((double) event.getX(), (double) event.getY());
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
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }

    @Override //add rendering calls to given method
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUPS(canvas);
        drawFPS(canvas);

        player.draw(canvas);
        joystick.draw(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta); //Color in color.XML file
        paint.setColor(color);
        paint.setTextSize(80);
        canvas.drawText("UPS: " + averageUPS, 100, 120, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta); //Color in color.XML file
        paint.setColor(color);
        paint.setTextSize(80);
        canvas.drawText("FPS: " + averageFPS, 100, 220, paint);
    }

    public void update() {
        joystick.update();
        player.update(joystick);
    }

}
