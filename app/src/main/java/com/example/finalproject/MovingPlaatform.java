package com.example.finalproject;

import android.graphics.Bitmap;

public class MovingPlaatform extends Sprite {
    private float velocity_x;
    private float Left_border,Right_border;

    public MovingPlaatform(float x, float y, float w, float h, Bitmap pic, float left_border, float right_border) {
        super(x, y, w, h, pic);
        Left_border = left_border;
        Right_border = right_border;
        velocity_x=9;
    }
    public void MovePlatform(){
        this.x+=velocity_x;
        if (getRight()>Right_border){
            velocity_x=-velocity_x;
        }
        if (getLeft()<Left_border){
          velocity_x=-velocity_x;
        }
    }
}
