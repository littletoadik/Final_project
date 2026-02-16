package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    FrameLayout frmMain;
    private CustomView cv;
     ArrayList<Integer> list =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startService(new Intent(this,PlayMusicService.class));

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference myRef = db.getReference("Level1").child("0");
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snap:snapshot.getChildren()){
                        int i= snap.getValue(Integer.class);
                        list.add(i);
                    }
                if (cv == null) {
                    cv = new CustomView(MainActivity.this, frmMain.getWidth(), frmMain.getHeight(), list);
                    frmMain.addView(cv);
                } else {
                    cv.invalidate(); //inshallah toda chat
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setContentView(R.layout.activity_main);
        frmMain=findViewById(R.id.frmMain);
    }
    }
