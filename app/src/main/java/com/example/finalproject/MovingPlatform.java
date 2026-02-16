package com.example.finalproject;

import android.graphics.Bitmap;

public class MovingPlatform extends Sprite {
    private float velocity_x;
    private float Left_border,Right_border;

    public MovingPlatform(float x, float y, float w, float h, Bitmap pic) {
        super(x, y, w, h, pic);
        Left_border = x;
        Right_border = x+1200;
    }
    public void MovePlatform(){
        this.x+=velocity_x;
        this.center_x=this.w/2+this.x;
        if (getRight()>Right_border){
            velocity_x=-velocity_x;
        }
        if (getLeft()<Left_border){
          velocity_x=-velocity_x;
        }
    }

    public void setVelocity_x(float velocity_x) {
        this.velocity_x = velocity_x;
    }
}
