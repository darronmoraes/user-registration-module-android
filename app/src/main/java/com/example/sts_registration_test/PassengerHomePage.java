package com.example.sts_registration_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class PassengerHomePage extends AppCompatActivity {

    BottomNavigationView bnView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_home_page);

        bnView = findViewById(R.id.bnView);

        bnView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =  item.getItemId();

                if (id == R.id.home){
                    loadFrag(new HomeFragment(), false);
                } else if (id == R.id.ticket) {
                    loadFrag(new TicketFragment(),false);
                } else if (id == R.id.schedule) {
                    loadFrag(new ScheduleFragment(),false);
                } else if (id == R.id.issues) {
                    loadFrag(new IssueReportingFragment(),false);
                }else {
                    loadFrag(new ProfileFragment(),true);
                }

                return true;
            }
        });

        /*bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id =  item.getItemId();

                if (id == R.id.home){
                    loadFrag(new HomeFragment(), false);
                } else if (id == R.id.ticket) {
                    loadFrag(new TicketFragment(),false);
                } else if (id == R.id.schedule) {
                    loadFrag(new ScheduleFragment(),false);
                } else if (id == R.id.issues) {
                    loadFrag(new IssueReportingFragment(),false);
                }else {
                    loadFrag(new ProfileFragment(),true);
                }

                return true;
            }
        });*/

        bnView.setSelectedItemId(R.id.profile);
    }

    public  void loadFrag(Fragment fragment, Boolean flag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag)
            ft.add(R.id.container, fragment);
        else
            ft.replace(R.id.container, fragment);
        ft.commit();
    }
}