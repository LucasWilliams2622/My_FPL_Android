package com.abc.myfplapplication.Model;

import java.util.Date;

public class ScheduleStudy {
    private String nameSubject;
    private String codeSubject;
    private String location;
    private String time;
    private Date dateSchedule;

    public ScheduleStudy(String nameSubject, String codeSubject, String location, String time, Date dateSchedule) {
        this.nameSubject = nameSubject;
        this.codeSubject = codeSubject;
        this.location = location;
        this.time = time;
        this.dateSchedule = dateSchedule;
    }


    public ScheduleStudy(String nameSubject, String codeSubject, String location, String time) {
        this.nameSubject = nameSubject;
        this.codeSubject = codeSubject;
        this.location = location;
        this.time = time;
    }

    public String getNameSubject() {
        return nameSubject;
    }

    public void setNameSubject(String nameSubject) {
        this.nameSubject = nameSubject;
    }

    public String getCodeSubject() {
        return codeSubject;
    }

    public void setCodeSubject(String codeSubject) {
        this.codeSubject = codeSubject;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(Date date) {
        this.dateSchedule = date;
    }
}
