package com.example.myfplapplication.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Notification implements Parcelable {
    public String title;
    public String description;
    public String time;
    public String imageUrl;
    public String status;

    public Notification(String title, String description, String time, String imageUrl, String status) {
        this.title = title;
        this.description = description;
        this.time = time;
        this.imageUrl = imageUrl;
        this.status = status;
    }

    protected Notification(Parcel in) {
        title = in.readString();
        description = in.readString();
        time = in.readString();
        imageUrl = in.readString();
        status = in.readString();
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
        dest.writeString(imageUrl);
        dest.writeString(status);
    }
}
