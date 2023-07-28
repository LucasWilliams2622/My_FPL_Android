package com.example.myfplapplication.Views.Fragment;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfplapplication.Adapter.NotificationAdapter;
import com.example.myfplapplication.Model.Notification;
import com.example.myfplapplication.Model.NotificationGroup;
import com.example.myfplapplication.R;
import com.example.myfplapplication.Service.APIService;
import com.example.myfplapplication.Service.UserService;
import com.example.myfplapplication.Views.Activities.MainActivity;
import com.example.myfplapplication.Views.ViewHolder.TabViewHolder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationFragment extends Fragment {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIService.base_link)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService apiService = retrofit.create(APIService.class);

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

        // Create a list of Notification objects
        List<Notification> notificationList = new ArrayList<>();
        UserService userService = new UserService(getContext());
        Call<ArrayList<Notification>> responseCall = apiService.GetNews((userService.getToken()));

        responseCall.enqueue(new Callback<ArrayList<Notification>>() {
            @Override
            public void onResponse(Call<ArrayList<Notification>> call, Response<ArrayList<Notification>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Notification> notifications = response.body();
                    Log.d("Notification", "onResponse: " + response.body());
                    for (Notification notification : notifications) {
                        notificationList.add(notification);
                    }

                    // Parse the date and time strings into Date objects
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    for (Notification notification : notificationList) {
                        String dateTimeString = notification.date + " " + notification.time;
                        Date dateTime = null;
                        try {
                            dateTime = dateFormat.parse(dateTimeString);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        notification.dateTime = dateTime;
                    }

                    // Sort the notifications by date and time
                    Collections.sort(notificationList, new Comparator<Notification>() {
                        @Override
                        public int compare(Notification n1, Notification n2) {
                            return n2.dateTime.compareTo(n1.dateTime);
                        }
                    });


                    // Group the notifications by date
                    Map<String, List<Notification>> groupedNotifications = new HashMap<>();
                    for (Notification notification : notificationList) {
                        String date = notification.date;
                        if (!groupedNotifications.containsKey(date)) {
                            groupedNotifications.put(date, new ArrayList<>());
                        }
                        groupedNotifications.get(date).add(notification);
                    }

                    // Create a list of NotificationGroup objects
                    List<NotificationGroup> notificationGroups = new ArrayList<>();
                    for (Map.Entry<String, List<Notification>> entry : groupedNotifications.entrySet()) {
                        String date = entry.getKey();
                        List<Notification> groupNotifications = entry.getValue();
                        NotificationGroup notificationGroup = new NotificationGroup(date, groupNotifications);
                        notificationGroups.add(notificationGroup);
                    }

                    // Parse the date strings into Date objects
                    SimpleDateFormat groupDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    for (NotificationGroup group : notificationGroups) {
                        Date date = null;
                        try {
                            date = groupDateFormat.parse(group.date);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                        group.dateObject = date;
                    }

                    // Sort the notification groups by date
                    Collections.sort(notificationGroups, new Comparator<NotificationGroup>() {
                        @Override
                        public int compare(NotificationGroup g1, NotificationGroup g2) {
                            return g2.dateObject.compareTo(g1.dateObject);
                        }
                    });


                    // Create an adapter to populate the list with notification groups
                    NotificationAdapter notificationAdapter = new NotificationAdapter(notificationGroups);
                    notificationAdapter.setOnItemClickListener(new NotificationAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(Notification notification) {
                            // Create a new instance of NotificationDetailFragment
                            NotificationDetailFragment fragment = new NotificationDetailFragment();

                            // Pass data to the new fragment using its arguments
                            Bundle args = new Bundle();
                            args.putString("title", notification.title);
                            args.putString("description", notification.description);
                            args.putString("time", notification.time);
                            args.putString("imageUrl", notification.imageUrl);
                            args.putString("status", notification.status);
                            fragment.setArguments(args);

                            // Use the replaceFragment method of MainActivity to switch to the new fragment
                            MainActivity mainActivity = (MainActivity) getActivity();
                            mainActivity.replaceFragment(fragment);
                        }
                    });

                    // Set the layout manager of the RecyclerView
                    notificationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    // Set the adapter of the RecyclerView
                    notificationRecyclerView.setAdapter(notificationAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Notification>> call, Throwable t) {
                Log.d("Notification", "onFailure: " + t.getMessage());
            }
        });

    }
}
