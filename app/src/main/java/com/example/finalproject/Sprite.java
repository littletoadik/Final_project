package com.example.finalproject;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite {
    protected float x,y;
    protected float center_x,center_y; // coordinates we are going to use
    protected float w,h; // height and width of the sprite
    protected Bitmap pic ;//image itself of the sprite

    public Sprite(float x, float y, float w, float h, Bitmap pic) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.center_x=this.w/2+x;
        this.center_y=this.h/2+y;
        this.pic =Bitmap.createScaledBitmap(pic,(int)w,(int)h,true);
    }

    public void draw(Canvas canvas){
        canvas.drawBitmap(pic,x,y,null);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        this.center_x=this.w/2+x;
    }

    public Bitmap getPic() {
        return pic;
    }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        this.center_y=this.h/2+y;
    }

    public float getLeft(){
        return center_x-w/2;
    }

    public float getRight(){
         return center_x+w/2;
    }

    public float getBottom(){
        return center_y+h/2;
    }

    public float getTop(){
        return center_y-h/2;
    }

    public void setLeft(float newLeft){
     center_x=newLeft+w/2;
     this.x=center_x-w/2;
    }

    public void setRight(float newRight){
        center_x=newRight-w/2;
        this.x=center_x-w/2;
    }

    public void setTop(float newTop){
        center_y=newTop-h/2;
        this.y=center_y+h/2;
    }

    public void setBottom(float newBottom){
        center_y=newBottom-h/2;
        this.y=center_y-h/2;
    }


    public boolean contains(float x,float y){
        return x>= this.x &&x<=this.x+w &&y>=this.y&&y<=this.y+h;
    }





}
