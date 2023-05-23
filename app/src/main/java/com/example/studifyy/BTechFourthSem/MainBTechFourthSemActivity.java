package com.example.studifyy.BTechFourthSem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.studifyy.BTechFirstSem.BTechFirstSemCoursesRecycleViewModal;
import com.example.studifyy.BTechFirstSem.MainBTechFirstSemActivity;
import com.example.studifyy.BTechSecondSem.BTechSecondSemCoursesRecycleViewAdapter;
import com.example.studifyy.BTechSecondSem.MainBTechSecondSemActivity;
import com.example.studifyy.BTechThirdSem.MainBTechThirdSemActivity;
import com.example.studifyy.MainActivity;
import com.example.studifyy.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainBTechFourthSemActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationview;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<BTechFirstSemCoursesRecycleViewModal> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_btech_fourth_sem);
        drawer = findViewById(R.id.drawer);
        list=new ArrayList<>();
        recyclerView=findViewById(R.id.recycle);
        navigationview = findViewById(R.id.naviagtionview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("B-Tech Fourth Semister");
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Design and Analysis of Algorithm"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Mathematics-4"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Discrete Structures"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Digital Electronic and logic Design"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Economics"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Fourth Sem Papers"));
        BTechFourthSemCoursesRecycleViewAdapter adapter=new  BTechFourthSemCoursesRecycleViewAdapter(list, this);
        recyclerView.setAdapter(adapter);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.FS) {
                    Intent intent1=new Intent(MainBTechFourthSemActivity.this, MainBTechFirstSemActivity.class);
                    startActivity(intent1);
                    finish();
                } else if (id == R.id.SS) {
                    Intent intent2=new Intent(MainBTechFourthSemActivity.this, MainBTechSecondSemActivity.class);
                    startActivity(intent2);
                    finish();
                } else if (id == R.id.TS) {
                    Intent intent3=new Intent(MainBTechFourthSemActivity.this, MainBTechThirdSemActivity.class);
                    startActivity(intent3);
                    finish();
                } else if (id == R.id.FourS) {
                    Intent intent4=new Intent(MainBTechFourthSemActivity.this, MainBTechFourthSemActivity.class);
                    startActivity(intent4);
                    finish();
                } else if (id == R.id.FifS) {
                    Toast.makeText(MainBTechFourthSemActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.SisS) {
                    Toast.makeText(MainBTechFourthSemActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.SevenS) {
                    Toast.makeText(MainBTechFourthSemActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                } else if(id==R.id.ES){
                    Toast.makeText(MainBTechFourthSemActivity.this, "LogOut", Toast.LENGTH_SHORT).show();
                }
                else {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(MainBTechFourthSemActivity.this, "LogOut Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainBTechFourthSemActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
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
                    }
                }
            });
        }

    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private boolean IsConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null&&connectivityManager.getActiveNetworkInfo().isConnected();

    }
}