package com.example.finalproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    FrameLayout frmMain;// to show the graphics
    private CustomView cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frmMain=findViewById(R.id.frmMain);


    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {// You need to create this function because the mainActicty isn't immediately created
        super.onWindowFocusChanged(hasFocus);
        if (cv==null &&hasFocus){
            cv =new CustomView(this,frmMain.getWidth(),frmMain.getHeight());//
            frmMain.addView(cv);


        }
    }
}