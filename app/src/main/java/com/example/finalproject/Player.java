package com.example.finalproject;

import android.graphics.Bitmap;

public class Player extends Sprite {

    private float velocity_x, velocity_y;// velocity of the player



    public Player(float velocity_x, float velocity_y, float x, float y, float w, float h, Bitmap pic ) {
        super(x, y, w, h, pic);
        this.velocity_x = 0;
        this.velocity_y = 0;
    }

    public float getVelocity_x() {
        return velocity_x;
    }

    public void setVelocity_x(float velocity_x) {
        this.velocity_x = velocity_x;
    }

    public float getVelocity_y() {
        return velocity_y;
    }

    public void setVelocity_y(float velocity_y) {
        this.velocity_y = velocity_y;
    }
}
