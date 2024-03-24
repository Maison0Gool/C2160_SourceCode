package com.example.playactivity;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Joystick {

    private final Paint outerCirclePaint;
    private final Paint innerCirclePaint;
    private int outerCircleRadius;
    private int innerCircleRadius;
    private int outerCircleCentrePositionX;
    private int outerCircleCentrePositionY;
    private int innerCircleCentrePositionX;
    private int innerCircleCentrePositionY;
    private double joystickCenterToTouchDistance;
    private boolean isPressed;
    private double actuatorX;
    private double actuatorY;

    @SuppressLint("ResourceAsColor")
    public Joystick(int centrePositionX, int centrePositionY, int outerCircleRadius, int innerCircleRadius) {
        //Outer and inner circle will make the joystick visual
        outerCircleCentrePositionX = centrePositionX;
        outerCircleCentrePositionY = centrePositionY;
        innerCircleCentrePositionX = centrePositionX;
        innerCircleCentrePositionY = centrePositionY;

        //Radii of Circle
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;

        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(Color.GRAY);
        outerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        innerCirclePaint = new Paint();
        innerCirclePaint.setColor(0xFF1B5E20);
        innerCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
    } 
    public void draw(Canvas canvas) {
        //draw outer Circle
        canvas.drawCircle(outerCircleCentrePositionX,
                outerCircleCentrePositionY,
                outerCircleRadius,
                outerCirclePaint);
        //draw inner Circle
        canvas.drawCircle(innerCircleCentrePositionX,
                innerCircleCentrePositionY,
                innerCircleRadius,
                innerCirclePaint);
    }

    public void update() {
        updateInnerCirclePosition();
    }

    private void updateInnerCirclePosition() {
        innerCircleCentrePositionX = (int) (outerCircleCentrePositionX + actuatorX * outerCircleRadius);
        innerCircleCentrePositionY = (int) (outerCircleCentrePositionY + actuatorY * outerCircleRadius);

    }

    public boolean isPressed(double touchPositionX, double touchPositionY) {
       joystickCenterToTouchDistance = Math.sqrt(
               Math.pow(outerCircleCentrePositionX - touchPositionX, 2) +
               Math.pow(outerCircleCentrePositionY - touchPositionY, 2)
       );
        return joystickCenterToTouchDistance < outerCircleRadius; //ep 5 | min 6
    }

    public void setIsPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    public boolean getIsPressed() {
        return isPressed;
    }

    public void setActuator(double touchPositionX, double touchPositionY) {
        double deltaX = touchPositionX - outerCircleCentrePositionX;
        double deltaY = touchPositionY - outerCircleCentrePositionY;
        double deltaDistance = Math.sqrt(Math.pow(deltaX, 2) +Math.pow(deltaY, 2));
        
        if (deltaDistance < outerCircleRadius) {
            actuatorX = deltaX/outerCircleRadius;
            actuatorY = deltaY/outerCircleRadius;
        } else {
            actuatorX = deltaX/deltaDistance;
            actuatorY = deltaY/deltaDistance;
        }
    }

    public void resetActuator() {
        actuatorX = 0.0;
        actuatorY = 0.0;
    }

    public double getActuatorX() {
        return actuatorX;
    }

    public double getActuatorY() {
        return actuatorY;
    }
}
