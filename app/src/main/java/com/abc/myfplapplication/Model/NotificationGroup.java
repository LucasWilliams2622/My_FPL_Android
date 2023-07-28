package com.abc.myfplapplication.Model;

import java.util.Date;
import java.util.List;

public class NotificationGroup {
    public String date;
    public List<Notification> notifications;
    public Date dateObject;

    public NotificationGroup(String date, List<Notification> notifications) {
        this.date = date;
        this.notifications = notifications;
    }
}

