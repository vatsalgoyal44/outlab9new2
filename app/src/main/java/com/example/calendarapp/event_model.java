package com.example.calendarapp;

public class event_model {

    private String Title;
    private String Description;
    private String Duration;
    private String date;
    private String time;
    private int ID;
    private String Type;

    public event_model(String title, String date, String time, String description, String duration,  String type) {
        this.Title = title;
        this.Description = description;
        this.Duration = duration;
        this.date = date;
        this.time = time;
        this.Type = type;
    }

    public event_model(int id, String title, String date, String time, String description, String duration,  String type) {
        this.ID=id;
        this.Title = title;
        this.Description = description;
        this.Duration = duration;
        this.date = date;
        this.time = time;
        this.Type = type;
    }

    @Override
    public String toString() {
        return "event_model{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", Duration='" + Duration + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", ID=" + ID +
                ", Type='" + Type + '\'' +
                '}';
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
