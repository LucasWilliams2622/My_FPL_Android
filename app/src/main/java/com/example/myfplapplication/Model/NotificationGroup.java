package com.example.myfplapplication.Model;

import java.util.List;

public class NotificationGroup {
    public String date;
    public List<Notification> notifications;

    public NotificationGroup(String date, List<Notification> notifications) {
        this.date = date;
        this.notifications = notifications;
    }
}
