package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class Edit_Event extends AppCompatActivity {
    private MaterialButton btnSubmit;
    EditText title,date,time,description,duration,submitdate,submittime;
    TextInputLayout eventtime,eventsubmittime,eventdate,eventsubmitdate,eventduration;
    DatePickerDialog.OnDateSetListener setListener;
    ImageButton datepicker, timepicker, submittimepicker,durationpicker, submitdatepicker;
    String Title,Date,Time,Description,Type,Duration,Action;
    TextView heading;
    event_model Curr_event;
    int ID;
    int page;
    int hour, minute;

    public static boolean validateJavaDate(String strDate)
    {
        /* Check if date is 'null' */
        if (strDate.trim().equals(""))
        {
            return true;
        }
        /* Date is not 'null' */
        else
        {
            /*
             * Set preferred date format,
             * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
            SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
            sdfrmt.setLenient(false);
            /* Create Date object
             * parse the string into date
             */
            try
            {
                Date javaDate = sdfrmt.parse(strDate);
                System.out.println(strDate+" is valid date format");
            }
            /* Date format is invalid */
            catch (ParseException e)
            {
                System.out.println(strDate+" is Invalid Date format");
                return false;
            }
            /* Return true if date format is valid */
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Type = extras.getString("type");
            System.out.println(extras.getInt("Id") + "Yahan print kiya hai ! ");
            ID = extras.getInt("Id");
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(ID + " Yahan edit_event mein ID print kiya hai1 ! ");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        heading=findViewById(R.id.eventTypeEdit);
        title = (EditText) findViewById(R.id.enterTitleedit);
        date = findViewById(R.id.enterDateedit);
        time = findViewById(R.id.enterTimeedit);
        description = findViewById(R.id.enterDescriptionedit);
        duration = findViewById(R.id.enterDurationedit);
        submitdate = findViewById(R.id.entersubmitDateedit);
        submittime = findViewById(R.id.entersubmitTimeedit);
        eventtime = findViewById(R.id.eventTimeedit);
        eventsubmittime = findViewById(R.id.eventsubmitTimeedit);
        eventdate = findViewById(R.id.eventDateedit);
        eventsubmitdate = findViewById(R.id.eventsubmitDateedit);
        eventduration = findViewById(R.id.eventDurationedit);
        datepicker = findViewById(R.id.datepickeredit);
        timepicker = findViewById(R.id.timepickeredit);
        submittimepicker = findViewById(R.id.subtimepickeredit);
        durationpicker = findViewById(R.id.durtimepickeredit);
        submitdatepicker = findViewById(R.id.subdatepickeredit);


        Calendar calendar = Calendar.getInstance();
        final int year  = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        /*setListener = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth){
                month++;
                String date = day + "/" + month +"/"+year;
                eventdate.setHelperText(date);
            }
        };*/

        if(Type.equals("assignment")){
            heading.setText("Edit Assignment");
            page = 2;
        }
        else if(Type.equals("exam_quiz")){
            heading.setText("Edit Exam or quiz");
            page = 1;
        }
        else if(Type.equals("lecture")){
            heading.setText("Edit Lecture");
            page =3;
        }
        else if(Type.equals("studyplan")){
            heading.setText("Edit Study Plan");
            page = 0;
        }


        DataBaseHelper dataBaseHelper = new DataBaseHelper(Edit_Event.this);
        Curr_event=dataBaseHelper.getEvent(ID);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(Curr_event.getID() + " Yahan ID edit_event mein print kiya hai1 ! ");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        title.setText(Curr_event.getTitle());
        date.setText(Curr_event.getDate());
        time.setText(Curr_event.getTime());
        description.setText(Curr_event.getDescription());
        duration.setText(Curr_event.getDuration());
        submitdate.setText(Curr_event.getDate());
        submittime.setText(Curr_event.getTime());

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Edit_Event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month++;
                        String dat;
                        if (month<10 && day<10){
                            dat= "0" + day + "/" + "0" + month +"/"+year;
                        }
                        else if (day<10){
                            dat= "0" + day + "/" + month +"/"+year;
                        }
                        else if (month<10){
                            dat= day + "/" + "0" +  month +"/"+year;
                        }
                        else dat = day + "/" + month +"/"+year;
                        date.setText(dat);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });

        submitdatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Edit_Event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month++;
                        String dat;
                        if (month<10 && day<10){
                            dat= "0" + day + "/" + "0" + month +"/"+year;
                        }
                        else if (day<10){
                            dat= "0" + day + "/" + month +"/"+year;
                        }
                        else if (month<10){
                            dat= day + "/" + "0" +  month +"/"+year;
                        }
                        else dat= day + "/" + month +"/"+year;
                        submitdate.setText(dat);
                    }
                }, year, month, day);
                datePickerDialog.show();

            }
        });




        if (Type.equals("assignment")) {
            eventtime.setVisibility(View.GONE);
            eventsubmittime.setVisibility(View.VISIBLE);
            submittime.setVisibility(View.VISIBLE);
            eventdate.setVisibility(View.GONE);
            eventsubmitdate.setVisibility(View.VISIBLE);
            submitdate.setVisibility(View.VISIBLE);
            eventduration.setVisibility(View.GONE);
            submittimepicker.setVisibility(View.VISIBLE);
            timepicker.setVisibility(View.GONE);
            datepicker.setVisibility(View.GONE);
            durationpicker.setVisibility(View.GONE);
            submitdatepicker.setVisibility(View.VISIBLE);
        }

        btnSubmit=findViewById(R.id.btnSubmitedit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Title=title.getText().toString();
                Date=date.getText().toString();
                Time=time.getText().toString();
                Description=description.getText().toString();
                Duration=duration.getText().toString();

                if (Type.equals("assignment")) {
                    Date=submitdate.getText().toString();
                    Time=submittime.getText().toString();
                    Duration="null";
                }

                Pattern timeP = Pattern.compile("([01][0-9]|2[0-3]):[0-5][0-9]");
                Pattern dateP = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");
                Matcher timeM= timeP.matcher(Time);
                Matcher  dateM= dateP.matcher(Date);
                Matcher  durationM= timeP.matcher(Duration);
                boolean timeB = timeM.matches();
                boolean dateB = dateM.matches();
                boolean durationB = durationM.matches();

                if(Duration.equals("")||Title.equals("")||Date.equals("")||Time.equals("")){
                    Toast.makeText(Edit_Event.this, "Please fill all * marked fields", Toast.LENGTH_SHORT).show();
                }
                else if(!timeB){
                    Toast.makeText(Edit_Event.this, "Please fill valid time in following format hh:mm or choose from clock button", Toast.LENGTH_SHORT).show();
                }
                else if(!validateJavaDate(Date)||!dateB){
                    Toast.makeText(Edit_Event.this, "Please fill valid date in following format dd/mm/yyyy or choose from calendar button", Toast.LENGTH_SHORT).show();
                }
                else if(!durationB && !Type.equals("assignment")){
                    Toast.makeText(Edit_Event.this, "Please fill valid duration in following format hh:mm or choose from clock button", Toast.LENGTH_SHORT).show();
                }
                else {
                    event_model new_event = new event_model(Curr_event.getID(),Title, Date, Time, Description, Duration, Type);

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(Edit_Event.this);
                    dataBaseHelper.editEvent(Type, new_event);

                    Intent intent = new Intent(Edit_Event.this, MainActivity.class);
                    intent.putExtra("pagenumber", page);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                time.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void popTimePicker2(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                submittime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }
    public void popTimePicker3(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                duration.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

}