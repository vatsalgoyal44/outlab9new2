package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.events.calendar.views.DatesGridLayout;
import com.events.calendar.views.EventsCalendar;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import java.text.DateFormatSymbols;


public class CalendarPage extends AppCompatActivity {

    DrawerLayout drawerLayout;
    CompactCalendarView eventsCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_page);

        drawerLayout = findViewById(R.id.drawer_layout);
        eventsCalendar = findViewById(R.id.customcal);
        /*HashMap<Object, Property> descHashMap = new HashMap<>();

        Property defaultproperty = new Property();

        defaultproperty.layoutResource = R.layout.default_view;

        defaultproperty.dateTextViewResource = R.id.text_viewd;
        descHashMap.put("default", defaultproperty);

        Property currentProperty = new Property();
        currentProperty.layoutResource = R.layout.currentview;
        currentProperty.dateTextViewResource = R.id.text_viewc;
        descHashMap.put("current", currentProperty);

        Property eventProperty = new Property();
        eventProperty.layoutResource = R.layout.present_view;
        eventProperty.dateTextViewResource = R.id.text_viewp;
        descHashMap.put("event", eventProperty);

        customCalendar.setMapDescToProp(descHashMap);

        HashMap<Integer, Object> dateHashMap = new HashMap<>();
        dateHashMap.put(calendar.get(Calendar.DAY_OF_MONTH), "current");
        dateHashMap.put(20, "event");
        dateHashMap.put(30, "event");

*/
        TextView selecteddate = findViewById(R.id.selectedDate);
        Calendar calendar = Calendar.getInstance();
        selecteddate.setText(calendar.get(Calendar.DAY_OF_MONTH) + " " + (new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)]) + " " + calendar.get(Calendar.YEAR));
        TextView month = findViewById(R.id.monthname);
        month.setText(new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)]);
        //eventsCalendar.setFirstDayOfWeek(Calendar.MONDAY);

        /*customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
                selecteddate.setText(selectedDate.get(Calendar.DAY_OF_MONTH) + " " + (new DateFormatSymbols().getMonths()[selectedDate.get(Calendar.MONTH)]) + " " + selectedDate.get(Calendar.YEAR));
            }
        });*/
        /*eventsCalendar.setOnClickListener(new EventsCalendar {
            @Override
            public void onClick(View v) {
                Calendar selectdate = eventsCalendar.getCurrentSelectedDate();
                selecteddate.setText(selectdate.get(Calendar.DAY_OF_MONTH) + " " + (new DateFormatSymbols().getMonths()[selectdate.get(Calendar.MONTH)]) + " " + selectdate.get(Calendar.YEAR));
            }
        });*/
        eventsCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                //List<Event> events = eventsCalendar.getEvents(dateClicked);
                selecteddate.setText(dateClicked.getDate() + " " + (new DateFormatSymbols().getMonths()[dateClicked.getMonth()]) + " " + (dateClicked.getYear()+1900));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                month.setText(new DateFormatSymbols().getMonths()[firstDayOfNewMonth.getMonth()]);
                selecteddate.setText(firstDayOfNewMonth.getDate() + " " + (new DateFormatSymbols().getMonths()[firstDayOfNewMonth.getMonth()]) + " " + (firstDayOfNewMonth.getYear()+1900));
                //Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
            }
        });

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

        closeDrawer(drawerLayout);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        finish();

        overridePendingTransition(0,0);
    }

    public void ClickCal(View view){

        recreate();
    }

    @Override
    protected void onPause(){
        super.onPause();

    }
}