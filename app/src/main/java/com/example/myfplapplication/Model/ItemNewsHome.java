package com.example.myfplapplication.Model;

import java.util.ArrayList;
import java.util.List;


public class ItemNewsHome {
    private String title;
    private String content;
    private int imageResId;

    public ItemNewsHome(String title, String content, int imageResId) {
        this.title = title;
        this.content = content;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getImageResId() {
        return imageResId;
    }
}
