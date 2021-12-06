package com.example.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Add_Event extends AppCompatActivity {
    private MaterialButton btnSubmit;
    EditText title,date,time,description,duration,submitdate,submittime;
    TextInputLayout eventtime,eventsubmittime,eventdate,eventsubmitdate,eventduration;
    String Title,Date,Time,Description,Type,Duration;
    TextView heading;
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
        }
        else if(Type.equals("exam_quiz")){
            heading.setText("Add Exam or quiz");
        }
        else if(Type.equals("lecture")){
            heading.setText("Add Lecture");
        }
        else if(Type.equals("studyplan")){
            heading.setText("Add Study Plan");
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

        if (Type.equals("assignment")) {
            eventtime.setVisibility(View.GONE);
            eventsubmittime.setVisibility(View.VISIBLE);
            eventdate.setVisibility(View.GONE);
            eventsubmitdate.setVisibility(View.VISIBLE);
            eventduration.setVisibility(View.GONE);
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

                event_model new_event= new event_model(Title,Date,Time,Description,Duration,Type);

                DataBaseHelper dataBaseHelper=new DataBaseHelper(Add_Event.this);
                dataBaseHelper.addEvent(Type,new_event);

                    Intent intent=new Intent(Add_Event.this,MainActivity.class);
                    startActivity(intent);
                    finish();

            }
        });

    }

}