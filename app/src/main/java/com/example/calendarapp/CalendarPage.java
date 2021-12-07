package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.time.YearMonth;
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


        final ImageButton showPreviousMonthBut = findViewById(R.id.previousmonth);
        final ImageButton showNextMonthBut = findViewById(R.id.nextmonth);

        showPreviousMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsCalendar.scrollLeft();
            }
        });

        showNextMonthBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsCalendar.scrollRight();
            }
        });


        TextView selecteddate = findViewById(R.id.selectedDate);
        Calendar calendar = Calendar.getInstance();
        selecteddate.setText(calendar.get(Calendar.DAY_OF_MONTH) + " " + (new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)]) + " " + calendar.get(Calendar.YEAR));
        TextView month = findViewById(R.id.monthname);
        month.setText(new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)]);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(getApplicationContext());
        int values[] = dataBaseHelper.getNumEvents(calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.YEAR));

        YearMonth yearMonthObject = YearMonth.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1);
        int daysInMonth = yearMonthObject.lengthOfMonth();
        for(int date = 1; date<=daysInMonth; date++){
            if(dataBaseHelper.isEvent(date+"/"+(calendar.get(Calendar.MONTH)+1) + "/" + calendar.get(Calendar.YEAR))){
                String str = (new DateFormatSymbols().getMonths()[calendar.get(Calendar.MONTH)]).substring(0,3)+" "+date+" "+calendar.get(Calendar.YEAR)+" 00:00:01.454 UTC";
                SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
                Date datesel = calendar.getTime();
                try{
                    datesel = df.parse(str);
                }
                catch (Exception e){
                    System.out.println("Something went wrong.");
                }
                long epoch = datesel.getTime();
                Event ev = new Event(Color.RED, epoch,"Some Event");
                eventsCalendar.addEvent(ev);
            }
        }



        TextView lec = findViewById(R.id.lectures);
        TextView studyplan = findViewById(R.id.studyplan);
        TextView exam = findViewById(R.id.exams);
        TextView assignm = findViewById(R.id.assnm);

        lec.setText("Lectures: "+values[2]);
        exam.setText("Exams: "+values[3]);
        studyplan.setText("Study Plan: "+values[0]);
        assignm.setText("Assignments: "+values[1]);


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
                int values[] = dataBaseHelper.getNumEvents(dateClicked.getDate() + "/" + (dateClicked.getMonth()+1) + "/" + (dateClicked.getYear()+1900));
                lec.setText("Lectures: "+values[2]);
                exam.setText("Exams: "+values[3]);
                studyplan.setText("Study Plan: "+values[0]);
                assignm.setText("Assignments: "+values[1]);

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

                month.setText(new DateFormatSymbols().getMonths()[firstDayOfNewMonth.getMonth()]);
                selecteddate.setText(firstDayOfNewMonth.getDate() + " " + (new DateFormatSymbols().getMonths()[firstDayOfNewMonth.getMonth()]) + " " + (firstDayOfNewMonth.getYear()+1900));
                //Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);
                int values[] = dataBaseHelper.getNumEvents(firstDayOfNewMonth.getDate() + "/" + (firstDayOfNewMonth.getMonth()+1) + "/" + (firstDayOfNewMonth.getYear()+1900));
                lec.setText("Lectures: "+values[2]);
                exam.setText("Exams: "+values[3]);
                studyplan.setText("Study Plan: "+values[0]);
                assignm.setText("Assignments: "+values[1]);

                YearMonth yearMonthObject = YearMonth.of(firstDayOfNewMonth.getYear()+1900, firstDayOfNewMonth.getMonth()+1);
                int daysInMonth = yearMonthObject.lengthOfMonth();
                eventsCalendar.removeAllEvents();
                for(int date = 1; date<=daysInMonth; date++){
                    if(dataBaseHelper.isEvent(date+"/"+(firstDayOfNewMonth.getMonth()+1) + "/" + (firstDayOfNewMonth.getYear()+1900))){
                        String str = (new DateFormatSymbols().getMonths()[firstDayOfNewMonth.getMonth()]).substring(0,3)+" "+date+" "+(firstDayOfNewMonth.getYear()+1900)+" 00:00:01.454 UTC";
                        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
                        Date datesel = calendar.getTime();
                        try{
                            datesel = df.parse(str);
                        }
                        catch (Exception e){
                            System.out.println("Something went wrong.");
                        }
                        long epoch = datesel.getTime();
                        Event ev = new Event(Color.RED, epoch,"Some Event");
                        eventsCalendar.addEvent(ev);
                    }
                }
            }
        });
        eventsCalendar.setAnimationListener(new CompactCalendarView.CompactCalendarAnimationListener() {
            @Override
            public void onOpened() {
            }

            @Override
            public void onClosed() {
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