package com.example.myfplapplication.Views.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myfplapplication.R;

public class NotificationDetailFragment extends Fragment {
    private String title;
    private String description;
    private String time;
    private String imageUrl;
    private String status;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retrieve data from the arguments
        Bundle args = getArguments();
        if (args != null) {
            title = args.getString("title");
            description = args.getString("description");
            time = args.getString("time");
            imageUrl = args.getString("imageUrl");
            status = args.getString("status");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification_detail, container, false);

        // Update your UI with the data
        TextView titleView = view.findViewById(R.id.notificationTitle);
        titleView.setText(title);

        // ...

        return view;
    }
}
