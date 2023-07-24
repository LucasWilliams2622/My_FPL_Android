package com.example.myfplapplication.Model;

import java.util.ArrayList;
import java.util.List;

public class StudyGroup {
    public  String date;

    public List<Study> studies;

    public StudyGroup(String date, List<Study> studies) {
        this.date = date;
        this.studies = studies;
    }
}
