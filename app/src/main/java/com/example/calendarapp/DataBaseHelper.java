package com.example.calendarapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String EVENT_TABLE = "EVENT_TABLE";
    public static final String EVENT_DESCRIPTION = "EVENT_DESCRIPTION";
    public static final String EVENT_TIME = "EVENT_TIME";
    public static final String EVENT_DATE = "EVENT_DATE";
    public static final String EVENT_TITLE = "EVENT_TITLE";
    public static final String EVENT_DURATION = "EVENT_DURATION";
    public static final String EVENT_TYPE = "EVENT_TYPE";
    public static final String EVENT_ID = "EVENT_ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "events.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement= "CREATE TABLE " + EVENT_TABLE + " (" + EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EVENT_TITLE + " TEXT, " + EVENT_DATE + " TEXT, " + EVENT_TIME + " TEXT, " + EVENT_DESCRIPTION + " TEXT, " + EVENT_DURATION + " TEXT, " + EVENT_TYPE + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addEvent(String type, event_model event){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(EVENT_TITLE, event.getTitle());
        cv.put(EVENT_DATE, event.getDate());
        cv.put(EVENT_TIME, event.getTime());
        cv.put(EVENT_DESCRIPTION, event.getDescription());
        cv.put(EVENT_TYPE, type);
        cv.put(EVENT_DURATION, event.getDuration());

        long insert = db.insert(EVENT_TABLE, null, cv);

        db.close();

        if(insert==(-1))return false;
        else return true;
    }

    public boolean deleteEvent(event_model event){

        SQLiteDatabase db = this.getReadableDatabase();
        String queryString="DELETE FROM " + EVENT_TABLE + " WHERE " + EVENT_ID + " = " + event.getID();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            cursor.close();
            db.close();
            return true;
        }
        else {
            cursor.close();
            db.close();
            return false;
        }

    }

    public ArrayList<event_model> getAssignments(){
        ArrayList<event_model> returnList=new ArrayList<>();

        String queryString="SELECT * FROM " + EVENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(6).equals("assignment")) {
                    int event_id = cursor.getInt(0);
                    String event_title = cursor.getString(1);
                    String event_date = cursor.getString(2);
                    String event_time = cursor.getString(3);
                    String event_description = cursor.getString(4);
                    String event_duration = cursor.getString(5);
                    String event_type = cursor.getString(6);

                    event_model newEvent = new event_model(event_id, event_title, event_date, event_time, event_description, event_duration, event_type);
                    returnList.add(newEvent);
                }
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<event_model> getStudyplan(){
        ArrayList<event_model> returnList=new ArrayList<>();

        String queryString="SELECT * FROM " + EVENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(6).equals("studyplan")) {
                    int event_id = cursor.getInt(0);
                    String event_title = cursor.getString(1);
                    String event_date = cursor.getString(2);
                    String event_time = cursor.getString(3);
                    String event_description = cursor.getString(4);
                    String event_duration = cursor.getString(5);
                    String event_type = cursor.getString(6);

                    event_model newEvent = new event_model(event_id, event_title, event_date, event_time, event_description, event_duration, event_type);
                    returnList.add(newEvent);
                }
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<event_model> getExam_quiz(){
        ArrayList<event_model> returnList=new ArrayList<>();

        String queryString="SELECT * FROM " + EVENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(6).equals("exam_quiz")) {
                    int event_id = cursor.getInt(0);
                    String event_title = cursor.getString(1);
                    String event_date = cursor.getString(2);
                    String event_time = cursor.getString(3);
                    String event_description = cursor.getString(4);
                    String event_duration = cursor.getString(5);
                    String event_type = cursor.getString(6);

                    event_model newEvent = new event_model(event_id, event_title, event_date, event_time, event_description, event_duration, event_type);
                    returnList.add(newEvent);
                }
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public ArrayList<event_model> getLecture(){
        ArrayList<event_model> returnList=new ArrayList<>();

        String queryString="SELECT * FROM " + EVENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(6).equals("lecture")) {
                    int event_id = cursor.getInt(0);
                    String event_title = cursor.getString(1);
                    String event_date = cursor.getString(2);
                    String event_time = cursor.getString(3);
                    String event_description = cursor.getString(4);
                    String event_duration = cursor.getString(5);
                    String event_type = cursor.getString(6);

                    event_model newEvent = new event_model(event_id, event_title, event_date, event_time, event_description, event_duration, event_type);
                    returnList.add(newEvent);
                }
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return returnList;
    }

    public int getNumAssignment(String Date){

        int num=0;

        String queryString="SELECT * FROM " + EVENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(2).equals(Date)) {
                    if (cursor.getString(6).equals("assignment")) {
                        num = num + 1;
                    }
                }
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return num;

    }

    public int getNumExam_quiz(String Date){

        int num=0;

        String queryString="SELECT * FROM " + EVENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(2).equals(Date)) {
                    if (cursor.getString(6).equals("exam_quiz")) {
                        num = num + 1;
                    }
                }
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return num;

    }

    public int getNumLecture(String Date){

        int num=0;

        String queryString="SELECT * FROM " + EVENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(2).equals(Date)) {
                    if (cursor.getString(6).equals("lecture")) {
                        num = num + 1;
                    }
                }
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return num;

    }

    public int getNumStudyplan(String Date){

        int num=0;

        String queryString="SELECT * FROM " + EVENT_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()){
            do{
                if(cursor.getString(2).equals(Date)) {
                    if (cursor.getString(6).equals("studyplan")) {
                        num = num + 1;
                    }
                }
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return num;

    }



}
