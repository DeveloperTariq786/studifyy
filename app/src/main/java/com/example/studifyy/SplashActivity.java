package com.example.studifyy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.studifyy.BTechFirstSem.MainBTechFirstSemActivity;
import com.example.studifyy.BTechThirdSem.MainBTechThirdSemActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {
    DatabaseReference reference;
    String pro,uni;
    private BroadcastReceiver castereceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if(!IsConnected()){
            Dialog dialog=new Dialog(this);
            dialog.show();
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialoglayout);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            Button tryAgain=dialog.findViewById(R.id.BtnDialog);
            tryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(IsConnected()){
                        dialog.dismiss();
                        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                            getData();
                        }
                        else {
                            Splash();
                        }
                    }
                }
            });
        }
        if (FirebaseAuth.getInstance().getCurrentUser()!=null){
            getData();
        }
        else {
            Splash();
        }
    }
    public void getData() {
        reference = FirebaseDatabase.getInstance().getReference("UserDetail");
        reference.child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    pro = String.valueOf(snapshot.child("programenrolled").getValue());
                    uni = String.valueOf(snapshot.child("universityattend").getValue());
                    if (uni.equals("Central University of Kashmir") && pro.equals("B-Tech in Computer Science")) {
                       Intent intent=new Intent(SplashActivity.this,MainBTechFirstSemActivity.class);
                       startActivity(intent);
                       finish();
                    } else if (uni.equals("Kashmir University") && pro.equals("BCA")) {
                        Intent intent1=new Intent(SplashActivity.this,MainBTechThirdSemActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                } else {
                    Toast.makeText(SplashActivity.this, "fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Splash(){
        Thread thread=new Thread(){
            public void run(){
                try {
                    sleep(1000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent intent=new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        };thread.start();
    }
    private boolean IsConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null&&connectivityManager.getActiveNetworkInfo().isConnected();

    }
}