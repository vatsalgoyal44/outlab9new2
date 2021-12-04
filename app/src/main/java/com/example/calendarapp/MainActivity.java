package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.drawer_layout);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        viewPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.AddFragment(new StudyPlan(), "StudyPlan");
        viewPagerAdapter.AddFragment(new Exams(), "Exams");
        viewPagerAdapter.AddFragment(new Assignments(), "Assignments");
        viewPagerAdapter.AddFragment(new Lectures(), "Lectures");


        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Study Plan");
        tabLayout.getTabAt(1).setText("Exams");
        tabLayout.getTabAt(2).setText("Assign-\nments");
        tabLayout.getTabAt(3).setText("Lectures");


    }

    public void ClickMenu(View view){

        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout){

        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickHome(View view){

        recreate();
    }

    public void ClickCal(View view){

        closeDrawer(drawerLayout);

        Intent intent = new Intent(this, CalendarPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        finish();

        overridePendingTransition(0,0);
    }
}