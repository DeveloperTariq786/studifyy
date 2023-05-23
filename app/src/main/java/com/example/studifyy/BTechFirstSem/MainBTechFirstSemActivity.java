package com.example.studifyy.BTechFirstSem;

import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studifyy.BTechFourthSem.MainBTechFourthSemActivity;
import com.example.studifyy.BTechSecondSem.MainBTechSecondSemActivity;
import com.example.studifyy.BTechThirdSem.MainBTechThirdSemActivity;
import com.example.studifyy.MainActivity;
import com.example.studifyy.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainBTechFirstSemActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView navigationview;
    Toolbar toolbar;
    RecyclerView recyclerView;
    ArrayList<BTechFirstSemCoursesRecycleViewModal> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(R.layout.activity_main_b_tech_sem_first_);
        drawer = findViewById(R.id.drawer);
        list=new ArrayList<>();
        recyclerView=findViewById(R.id.recycle);
        navigationview = findViewById(R.id.naviagtionview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("B-Tech First Semister");
        }
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Computer Programming"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Mathematics-1"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Physics"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Drawing"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Chemistry"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Sociology"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("Labs"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("WorkShop"));
        list.add(new BTechFirstSemCoursesRecycleViewModal("First Sem Papers"));
        BTechFirstSemCoursesRecycleViewAdapter adapter=new BTechFirstSemCoursesRecycleViewAdapter(list, this);
        recyclerView.setAdapter(adapter);
        navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.FS) {
                    Intent intent1=new Intent(MainBTechFirstSemActivity.this,MainBTechFirstSemActivity.class);
                    startActivity(intent1);
                    finish();
                } else if (id == R.id.SS) {
                    Intent intent2=new Intent(MainBTechFirstSemActivity.this, MainBTechSecondSemActivity.class);
                    startActivity(intent2);
                    finish();
                } else if (id == R.id.TS) {
                    Intent intent3=new Intent(MainBTechFirstSemActivity.this,MainBTechThirdSemActivity.class);
                    startActivity(intent3);
                    finish();
                } else if (id == R.id.FourS) {
                    Intent intent4=new Intent(MainBTechFirstSemActivity.this, MainBTechFourthSemActivity.class);
                    startActivity(intent4);
                    finish();
                } else if (id == R.id.FifS) {
                    Toast.makeText(MainBTechFirstSemActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.SisS) {
                    Toast.makeText(MainBTechFirstSemActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();

                } else if (id == R.id.SevenS) {
                    Toast.makeText(MainBTechFirstSemActivity.this, "Coming Soon", Toast.LENGTH_SHORT).show();
                } else if(id==R.id.ES){
                    Toast.makeText(MainBTechFirstSemActivity.this, "LogOut", Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(MainBTechFirstSemActivity.this, "LogOut Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(MainBTechFirstSemActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                /*else {
                    Intent intent=new Intent(MainBTechFirstSemActivity.this, ChatGPTActivity.class);
                    startActivity(intent);
                    finish();
                }*/
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
