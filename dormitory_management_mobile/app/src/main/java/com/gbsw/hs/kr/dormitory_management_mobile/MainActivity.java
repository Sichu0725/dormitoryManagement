package com.gbsw.hs.kr.dormitory_management_mobile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Fragment student_card = new student_card_fragment();
    private Fragment notification = new notification_fragment();
    private Fragment my_info = new my_info_fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.student_card:
                        setFrag(0);
                        break;
                    case R.id.notification:
                        setFrag(1);
                        break;
                    case R.id.my_info:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });
        setFrag(0);



    }

    private void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.MainFrame,student_card);
                ft.commit();
                break;

            case 1:
                ft.replace(R.id.MainFrame,notification);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.MainFrame,my_info);
                ft.commit();
                break;


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}