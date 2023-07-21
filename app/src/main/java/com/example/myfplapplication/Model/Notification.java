package com.example.myfplapplication.Model;

public class Notification {
    public String title;
    public String description;
    public String time;
    public String image;
    public String status;

    public Notification(String title, String description, String time, String image, String status) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.image = image;
        this.status = status;
    }
}
