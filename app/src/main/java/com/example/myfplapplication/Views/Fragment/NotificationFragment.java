package com.example.myfplapplication.Views.Fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfplapplication.Adapter.NotificationAdapter;
import com.example.myfplapplication.Model.Notification;
import com.example.myfplapplication.Model.NotificationGroup;
import com.example.myfplapplication.R;
import com.example.myfplapplication.Views.ViewHolder.TabViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class NotificationFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    // Tab
        RecyclerView tabList = view.findViewById(R.id.tab_list);
        tabList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // Create a list of tab labels
        List<String> tabLabels = new ArrayList<>();
        tabLabels.add("Tất cả");
        tabLabels.add("Cá nhân");
        tabLabels.add("Học tập");
        tabLabels.add("Hoạt động");
        tabLabels.add("Học phí");

        // Create an adapter to populate the list with tabs
        RecyclerView.Adapter tabAdapter = new RecyclerView.Adapter<TabViewHolder>() {
            private int selectedPosition = 0;
            @NonNull
            @Override
            public TabViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                TextView textView = new TextView(parent.getContext());
                ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(8, 1, 8, 1);
                textView.setLayoutParams(layoutParams);
                textView.setPadding(64, 20, 64, 20);

                // Set the background color and corner radius of the tab
                GradientDrawable background = new GradientDrawable();
                background.setColor(Color.TRANSPARENT);
                background.setCornerRadius(50);
                background.setStroke(2, Color.parseColor("#F26F25"));
                textView.setBackground(background);

                // Set an OnClickListener to handle tab clicks
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag();
                        if (position != selectedPosition) {
                            selectedPosition = position;
                            notifyDataSetChanged();
                        }
                    }
                });

                return new TabViewHolder(textView);
            }

            @Override
            public void onBindViewHolder(@NonNull TabViewHolder holder, int position) {
                holder.textView.setText(tabLabels.get(position));
                holder.textView.setTag(position);

                // Customize the appearance of the tab based on its state
                if (position == selectedPosition) {
                    holder.textView.setTextColor(Color.WHITE);

                    GradientDrawable background = (GradientDrawable) holder.textView.getBackground();
                    background.setColor(Color.parseColor("#F26F25"));
                    background.setStroke(2, Color.parseColor("#F26F25"));
                } else {
                    holder.textView.setTextColor(Color.parseColor("#F26F25"));

                    GradientDrawable background = (GradientDrawable) holder.textView.getBackground();
                    background.setColor(Color.TRANSPARENT);
                    background.setStroke(2, Color.parseColor("#F26F25"));
                }
            }

            @Override
            public int getItemCount() {
                return tabLabels.size();
            }
        };

        // Set the adapter of the RecyclerView
        tabList.setAdapter(tabAdapter);

    // Notification
        RecyclerView notificationRecyclerView = view.findViewById(R.id.notification_list);

        // Create a list of notification groups
        List<NotificationGroup> notificationGroups = new ArrayList<>();
        notificationGroups.add(new NotificationGroup("2023-07-19", Arrays.asList(
                new Notification("Thông báo lịch học chuyên ngành...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius ...", "10:35", "https://example.com/example.jpg", "unread"),
                new Notification("Thông báo đăng ký thực hiện dự án...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius ...", "09:25", "https://example.com/example.jpg", "unread"),
                new Notification("Phòng CTSV thông báo nhắc nhở DS...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius ...", "09:22", "https://example.com/example.jpg", "read")
        )));
        notificationGroups.add(new NotificationGroup("2023-07-18", Arrays.asList(
                new Notification("Bạn đã điểm danh thành công...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius ...", "10:21", "https://example.com/example.jpg", "read"),
                new Notification("Bạn đã điểm danh thành công...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius ...", "10:21", "https://example.com/example.jpg", "read")
        )));
        notificationGroups.add(new NotificationGroup("2023-07-17", Arrays.asList(
                new Notification("Thông báo lịch học môn PDP102 khóa...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius ...", "16:05", "https://example.com/example.jpg", "read"),
                new Notification("Bạn đã điểm danh thành công...", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec varius ...", "10:21", "https://example.com/example.jpg", "read")
        )));

        // Create an adapter to populate the list with notification groups
        NotificationAdapter notificationAdapter = new NotificationAdapter(notificationGroups);

        // Set the layout manager of the RecyclerView
        notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set the adapter of the RecyclerView
        notificationRecyclerView.setAdapter(notificationAdapter);
    }
}
