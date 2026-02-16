package com.example.finalproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class CustomView extends SurfaceView implements Runnable{// runnable for threds,surface view to show graphics
    private Context context;
    private Thread thread;
    private float wScreen, hScreen;// screen size
    private SurfaceHolder holder;
    //private int [][] list= new int[7][8];
    ArrayList<Integer> test=new ArrayList<>();
    private Level level;// game logic
    private Canvas canvas;// if there are problems of the app crashing this might be the problem


    public CustomView(Context context, float wScreen, float hScreen, ArrayList<Integer> test) {
        super(context);

        level=new Level(context,wScreen,hScreen,test);
        holder=getHolder();
        thread=new Thread(this);
        thread.start();

    }

    public void drawSurface(){
        if (holder.getSurface().isValid()){
            canvas =holder.lockCanvas();
            level.moveScreen(canvas);
            canvas.drawColor(Color.WHITE);
            level.draw(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex=event.getX();
        float ey=event.getY();
        level.touchUpadte(ex,ey,event.getAction());
        return true;
    }

    @Override
    public void run() {
    while (true){
        drawSurface();
        level.update();
        try {
            Thread.sleep(20); // so the game won't update every frame
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    }
}
