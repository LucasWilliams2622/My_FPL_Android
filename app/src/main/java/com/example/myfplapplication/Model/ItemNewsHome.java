package com.example.myfplapplication.Model;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;


public class ItemNewsHome {
    private String title;
    private String content;
    private String imageUrl;

    public ItemNewsHome(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getShortTitle() {
        int shortLength = 20;
        if (title.length() <= shortLength + 2) {
            return title;
        } else {
            return title.substring(0, shortLength) + "...";
        }
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }
}
