package com.abc.myfplapplication.Model;

public class ScheduleExam {
    private String nameSubject;
    private String codeSubject;
    private  String location;
    private  String dateSchedule;
    private  String time;

    public ScheduleExam(String nameSubject, String codeSubject, String location, String dateSchedule, String time) {
        this.nameSubject = nameSubject;
        this.codeSubject = codeSubject;
        this.location = location;
        this.dateSchedule = dateSchedule;
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

    public String getDateSchedule() {
        return dateSchedule;
    }

    public void setDateSchedule(String dateSchedule) {
        this.dateSchedule = dateSchedule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
