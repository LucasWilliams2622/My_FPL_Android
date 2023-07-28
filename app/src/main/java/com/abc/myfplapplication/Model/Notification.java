package com.abc.myfplapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Notification implements Parcelable {
    public String title;
    public String description;
    public String time;
    public String date;
    public String imageUrl;
    public String status;
    public String category;
    public Date dateTime;

    public Notification(String title, String description, String time, String date, String imageUrl, String status, String category) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.date = date;
        this.imageUrl = imageUrl;
        this.status = status;
        this.category = category;
    }

    protected Notification(Parcel in) {
        title = in.readString();
        description = in.readString();
        time = in.readString();
        date = in.readString();
        imageUrl = in.readString();
        status = in.readString();
        category = in.readString();
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(time);
        dest.writeString(date);
        dest.writeString(imageUrl);
        dest.writeString(status);
        dest.writeString(category);
    }
}
