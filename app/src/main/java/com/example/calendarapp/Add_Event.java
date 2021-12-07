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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.*;


public class Add_Event extends AppCompatActivity {
    private MaterialButton btnSubmit;
    EditText title,date,time,description,duration,submitdate,submittime;
    TextInputLayout eventtime,eventsubmittime,eventdate,eventsubmitdate,eventduration;
    DatePickerDialog.OnDateSetListener setListener;
    ImageButton datepicker, timepicker, submittimepicker,durationpicker, submitdatepicker;
    String Title,Date,Time,Description,Type,Duration,Action;
    TextView heading;
    int hour, minute;
    int page;

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
                java.util.Date javaDate = sdfrmt.parse(strDate);
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
        setContentView(R.layout.activity_add_event);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Type = extras.getString("type");

        }

        heading=findViewById(R.id.eventtype);

        if(Type.equals("assignment")){
            heading.setText("Add Assignment");
            page = 2;
        }
        else if(Type.equals("exam_quiz")){
            heading.setText("Add Exam or quiz");
            page = 1;
        }
        else if(Type.equals("lecture")){
            heading.setText("Add Lecture");
            page = 3;
        }
        else if(Type.equals("studyplan")){
            heading.setText("Add Study Plan");
            page = 0;
        }

        title = (EditText) findViewById(R.id.enterTitle);
        date = findViewById(R.id.enterDate);
        time = findViewById(R.id.enterTime);
        description = findViewById(R.id.enterDescription);
        duration = findViewById(R.id.enterDuration);
        submitdate = findViewById(R.id.entersubmitDate);
        submittime = findViewById(R.id.entersubmitTime);
        eventtime = findViewById(R.id.eventTime);
        eventsubmittime = findViewById(R.id.eventsubmitTime);
        eventdate = findViewById(R.id.eventDate);
        eventsubmitdate = findViewById(R.id.eventsubmitDate);
        eventduration = findViewById(R.id.eventDuration);
        datepicker = findViewById(R.id.datepicker);
        timepicker = findViewById(R.id.timepicker);
        submittimepicker = findViewById(R.id.subtimepicker);
        durationpicker = findViewById(R.id.durtimepicker);
        submitdatepicker = findViewById(R.id.subdatepicker);


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

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Add_Event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month++;
                        String dat = day + "/" + month +"/"+year;
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
                        Add_Event.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month++;
                        String dat = day + "/" + month +"/"+year;
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

        btnSubmit=findViewById(R.id.btnSubmit);
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
                //Pattern dateP = Pattern.compile("^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$");
                Matcher timeM= timeP.matcher(Time);
                //Matcher  dateM= dateP.matcher(Date);
                Matcher  durationM= timeP.matcher(Duration);
                boolean timeB = timeM.matches();
                //boolean dateB = dateM.matches();
                boolean durationB = durationM.matches();

                if(Duration.equals("")||Title.equals("")||Date.equals("")||Time.equals("")){
                    Toast.makeText(Add_Event.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else if(!timeB){
                    Toast.makeText(Add_Event.this, "Please fill time in following format hh:mm or choose from clock button", Toast.LENGTH_SHORT).show();
                }
                else if(!validateJavaDate(Date)){
                    Toast.makeText(Add_Event.this, "Please fill date in following format dd/mm/yyyy or choose from calendar button", Toast.LENGTH_SHORT).show();
                }
                else if(!durationB && !Type.equals("assignment")){
                    Toast.makeText(Add_Event.this, "Please fill duration in following format hh:mm or choose from clock button", Toast.LENGTH_SHORT).show();
                }
                else {
                    event_model new_event = new event_model(Title, Date, Time, Description, Duration, Type);

                    DataBaseHelper dataBaseHelper = new DataBaseHelper(Add_Event.this);
                    dataBaseHelper.addEvent(Type, new_event);

                    Intent intent = new Intent(Add_Event.this, MainActivity.class);
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