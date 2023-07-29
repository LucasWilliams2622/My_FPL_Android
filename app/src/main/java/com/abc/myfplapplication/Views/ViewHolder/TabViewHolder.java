package com.abc.myfplapplication.Views.ViewHolder;

import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TabViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public TabViewHolder(TextView textView) {
        super(textView);
        this.textView = textView;
    }
}

