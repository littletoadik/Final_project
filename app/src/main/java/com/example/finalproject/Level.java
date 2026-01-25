package com.example.finalproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Level {
    private float wScreen,hScreen;// size of the level
    private Context context;

    private Bitmap picBlock,picPlayer,picRight,picLeft,picUp,picSpike;

    private ArrayList<Sprite> list_blocks =new ArrayList<>();
    private ArrayList<Sprite> list_Spikes =new ArrayList<>();

    private Player player;

    private Sprite Right,Left,Up;

    private int [][] arr;

    private final float Warr=150;// width of one col
    private final float Harr=135;// height of one row

    private final float Gravity=0.6f;

    private final int arrSizex =50;
    private final int arrSizey=8;

    private final float Move_Speed=9;
    private final float Jump_Speed=-20;

    private float offset_X =0; // configures the top left corner

    private final float Right_Margin=400;
    private final float Left_Margin=60;



    public Level(Context context, float wScreen, float hScreen) {
        arr= new int[arrSizex][arrSizey];
        this.wScreen = wScreen;
        this.hScreen = hScreen;
        this.context = context;

        picBlock= BitmapFactory.decodeResource(context.getResources(),R.drawable.brick_wall);// init of the pic
        picPlayer=BitmapFactory.decodeResource(context.getResources(), R.drawable.den_tivbenkel);
        picRight=BitmapFactory.decodeResource(context.getResources(), R.drawable.right_arrow);
        picLeft=BitmapFactory.decodeResource(context.getResources(), R.drawable.left_arrow);
        picUp=BitmapFactory.decodeResource(context.getResources(), R.drawable.up_arrow);
        picSpike=BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_image);

        Right=new Sprite(Warr,50,Warr,Harr,picRight);
        Left=new Sprite(0,50,Warr,Harr,picLeft);
        Up=new Sprite(Warr*2,50,Warr,Harr,picUp);

        for (int i=0;i< arrSizex;i++){
            for (int j=0;j<arrSizey;j++){
                arr[i][j]=0;
            }
        }

        arr[4][1]=2;
        arr[15][7]=1;
        arr[4][7]=1;
        arr[3][7]=1;
        arr[5][7]=1;
        arr[3][6]=1;
        arr[16][7]=1;
        arr[4][4]=1;
        arr[6][7]=1;
        arr[7][7]=1;
        arr[8][7]=1;
        arr[9][7]=1;
        arr[10][7]=1;
        arr[11][7]=1;
        arr[11][6]=3;
        arr[12][7]=1;
        arr[13][7]=1;


        for (int i = 0; i< arrSizex; i++) {
            for (int j = 0; j < arrSizey; j++) {
                if (arr[i][j]==1) {
                    list_blocks.add(new Sprite(Warr * i, Harr * j, Warr, Harr, picBlock));
                }
                if (arr[i][j]==3){
                    list_Spikes.add(new Sprite(Warr * i, Harr * j, Warr, Harr, picSpike));
                }
                if (arr[i][j]==2){
                   player= new Player(-6,3,Warr * i, Harr * j, Warr, Harr, picPlayer);
                }
            }
        }


    }

    public void touchUpadte(float ex, float ey, int action) {
    if (action== MotionEvent.ACTION_DOWN) {
        if (Right.contains(ex+offset_X, ey)) {
            player.setVelocity_x(Move_Speed);
        }
        if (Left.contains(ex+offset_X,ey)){
            player.setVelocity_x(-Move_Speed);
        }
        if (Up.contains(ex+offset_X,ey)&&isOnPlatform()){
            player.setVelocity_y(Jump_Speed);
        }
    }
    else if(action==MotionEvent.ACTION_UP){
        player.setVelocity_x(0);
    }
    }

    public void draw (Canvas canvas){
    for (int i = 0; i< list_blocks.size(); i++){
        list_blocks.get(i).draw(canvas);
    }
    for (int j=0; j< list_Spikes.size(); j++){
        list_Spikes.get(j).draw(canvas);
    }
    player.draw(canvas);

    Right.draw(canvas);
    Left.draw(canvas);
    Up.draw(canvas);

    }

    public void moveScreen(Canvas canvas){
         float right_boundary= offset_X +canvas.getWidth()-Right_Margin;
         if (player.getRight()>right_boundary){
             offset_X+=player.getRight()-right_boundary;
             Right.setX(offset_X+Warr);
             Left.setX(offset_X);
             Up.setX(offset_X+Warr*2);
         }
         float left_boundary=offset_X+Left_Margin;
         if (player.getLeft()<left_boundary){
             offset_X-=left_boundary-player.getLeft();
             Right.setX(offset_X+Warr);
             Left.setX(offset_X);
             Up.setX(offset_X+Warr*2);
         }
         canvas.translate(-offset_X,0);

    }

    public boolean isOnPlatform(){
        player.setY(player.getY()+5);
        ArrayList<Sprite> col_list=checkCollisionArray(player, list_blocks);
        player.setY(player.getY()-5);
        if (col_list.size()>0){
            return true;
        }
        else
            return false;
    }

    public void update() {
    resolveCollision();
    resolveLoss();
    }

    public void resolveCollision(){
    player.setY(player.getY()+player.getVelocity_y());
    player.setVelocity_y(player.getVelocity_y()+Gravity);
        ArrayList <Sprite> col_list=checkCollisionArray(player, list_blocks);
        if (col_list.size()>0){
            Sprite collided =col_list.get(0);
               if (player.getVelocity_y()>0){
                player.setBottom(collided.getTop());
            }
            else if(player.getVelocity_y()<0){
                player.setTop(collided.getBottom());
            }
            player.setVelocity_y(0);
        }
        player.setX(player.getX()+player.getVelocity_x());

        col_list=checkCollisionArray(player, list_blocks);
        if (col_list.size()>0){
            col_list=checkCollisionArray(player, list_blocks);
            Sprite collided =col_list.get(0);
            if (player.getVelocity_x()>0){
                player.setRight(collided.getLeft());
            }
            else if(player.getVelocity_x()<0){
                player.setLeft(collided.getRight());
            }
            player.setVelocity_x(0);
        }

    }
    public void resolveLoss(){
     ArrayList<Sprite> col_list=checkCollisionArray(player,list_Spikes);
     if (col_list.size()>0){
        player.setX(Warr*4);
        player.setY(Harr);
        offset_X=0;
         Right.setX(offset_X+Warr);
         Left.setX(offset_X);
         Up.setX(offset_X+Warr*2);
         player.setVelocity_y(0);
     }
    }


    public boolean checkCollision(Sprite s1,Sprite s2){
    boolean NoCollisionX=s1.getRight()<=s2.getLeft()||s1.getLeft()>=s2.getRight();
    boolean NoCollisionY=s1.getBottom()<=s2.getTop()||s1.getTop()>=s2.getBottom();
     if (NoCollisionY||NoCollisionX){
         return false;
     }
     return true;
    }

    public ArrayList<Sprite>checkCollisionArray(Sprite sprite,ArrayList<Sprite>list){
        ArrayList<Sprite> collision_list=new ArrayList<Sprite>();
        for (int i=0;i<list.size();i++){
            if (checkCollision(sprite,list.get(i))){
                collision_list.add(list.get(i));
            }
        }
        return collision_list;
    }



}
