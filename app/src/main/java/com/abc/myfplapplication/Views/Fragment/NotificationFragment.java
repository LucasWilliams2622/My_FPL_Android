package com.abc.myfplapplication.Views.Fragment;

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

import com.abc.myfplapplication.Adapter.NotificationAdapter;
import com.abc.myfplapplication.Model.Notification;
import com.abc.myfplapplication.Model.NotificationGroup;
import com.abc.myfplapplication.R;
import com.abc.myfplapplication.Views.Activities.MainActivity;
import com.abc.myfplapplication.Views.ViewHolder.TabViewHolder;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        try {
            String response = "{\n" +
                    "\t\"notifications\": [{\n" +
                    "\t\t\t\"title\": \"THÔNG BÁO KẾ HOẠCH BẢO VỆ DỰ ÁN TỐT NGHIỆP_HK SUMMER 2023 \",\n" +
                    "\t\t\t\"description\": \"Sinh viên thực hiện dự án tốt nghiệp học kỳ Summer 2023 vui lòng xem kế hoạch bảo vệ và danh sách hội đồng tại đây.Sinh viên chuẩn bị báo cáo và trình bày theo yêu cầu của giảng viên hướng dẫn.\",\n" +
                    "\t\t\t\"time\": \"10: 35\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 19\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"unread\",\n" +
                    "\t\t\t\"category\": \"Học tập\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"THÔNG BÁO LỊCH HỌC MÔN PDP102 KHÓA 19.3 HỌC KỲ SUMMER 2023(BLOCK 2)\",\n" +
                    "\t\t\t\"description\": \"Sinh viên khóa 19.3 đăng ký học môn PDP102 học kỳ Summer 2023(block 2) vui lòng xem lịch học và phòng học tại đây.Sinh viên có mặt đúng giờ và mang theo thẻ sinh viên khi đi học.\",\n" +
                    "\t\t\t\"time\": \"09: 25\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 19\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"unread\",\n" +
                    "\t\t\t\"category\": \"Học tập\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"THÔNG BÁO ĐĂNG KÝ THỰC HIỆN DỰ ÁN TỐT NGHIỆP HỌC KỲ FALL 2023 \",\n" +
                    "\t\t\t\"description\": \"Sinh viên khóa 19.3 có ý định thực hiện dự án tốt nghiệp học kỳ Fall 2023 vui lòng đăng ký thông tin và chọn giảng viên hướng dẫn tại đây.Thời hạn đăng ký là từ ngày 20 / 7 / 2023 đến ngày 31 / 7 / 2023.\",\n" +
                    "\t\t\t\"time\": \"09: 22\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 19\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"read\",\n" +
                    "\t\t\t\"category\": \"Học tập\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"Bạn đã điểm danh thành công môn Android Networking \",\n" +
                    "\t\t\t\"description\": \"Bạn đã được ghi nhận có mặt trong buổi học môn Android Networking vào ngày 18 / 7 / 2023. Điểm danh là một trong những yếu tố quan trọng để tính điểm chuyên cần của bạn.\",\n" +
                    "\t\t\t\"time\": \"10: 21\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 18\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"read\",\n" +
                    "\t\t\t\"category\": \"Cá nhân\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"Bạn đã điểm danh thành công môn Lập trình đa nền tảng \",\n" +
                    "\t\t\t\"description\": \"Bạn đã được ghi nhận có mặt trong buổi học môn Lập trình đa nền tảng vào ngày 18 / 7 / 2023. Điểm danh là một trong những yếu tố quan trọng để tính điểm chuyên cần của bạn.\",\n" +
                    "\t\t\t\"time\": \"10: 21\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 18\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"read\",\n" +
                    "\t\t\t\"category\": \"Cá nhân\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"PHÒNG CTSV THÔNG BÁO QUYẾT ĐỊNH KHEN THƯỞNG SINH VIÊN TIÊU BIỂU HỌC KỲ FALL 2022 \",\n" +
                    "\t\t\t\"description\": \"Phòng CTSV xin chúc mừng các sinh viên có tên trong danh sách được khen thưởng sinh viên tiêu biểu học kỳ Fall 2022. Đây là phần thưởng xứng đáng cho những nỗ lực và thành tích của các bạn trong học tập và hoạt động.\",\n" +
                    "\t\t\t\"time\": \"16: 05\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 17\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"read\",\n" +
                    "\t\t\t\"category\": \"Hoạt động\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"P.DVSV - THÔNG BÁO: V / V TRIỂN KHAI CHỨC NĂNG ĐÁNH GIÁ CHẤT LƯỢNG DỊCH VỤ TRỰC TUYẾN TRÊN NỀN TẢNG AP \",\n" +
                    "\t\t\t\"description\": \"Phòng DVSV thông báo triển khai chức năng đánh giá chất lượng dịch vụ trực tuyến trên nền tảng AP.Sinh viên có thể truy cập vào AP để đánh giá chất lượng dịch vụ của các phòng ban, giảng viên và nhân viên.Thời gian đánh giá là từ ngày 17 / 7 / 2023 đến ngày 31 / 7 / 2023. \",\n" +
                    "\t\t\t\"time\": \"10: 21\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 17\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"read\",\n" +
                    "\t\t\t\"category\": \"Hoạt động\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"THÔNG BÁO HỌC BỔNG TOÀN PHẦN DU HỌC SINGAPORE NĂM 2023 \",\n" +
                    "\t\t\t\"description\": \"Phòng CTSV thông báo cơ hội học bổng toàn phần du học Singapore năm 2023 dành cho sinh viên xuất sắc của trường.Sinh viên có thể xem điều kiện và hồ sơ đăng ký tại đây.Thời hạn nộp hồ sơ là ngày 31 / 8 / 2023.\",\n" +
                    "\t\t\t\"time\": \"15: 32\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 16\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"unread\",\n" +
                    "\t\t\t\"category\": \"Học tập\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"THÔNG BÁO THAM GIA CUỘC THI LẬP TRÌNH HACKATHON 2023 \",\n" +
                    "\t\t\t\"description\": \"Phòng CTSV thông báo tổ chức cuộc thi lập trình Hackathon 2023 dành cho sinh viên yêu thích công nghệ.Sinh viên có thể đăng ký tham gia theo nhóm hoặc cá nhân tại đây.Cuộc thi sẽ diễn ra vào ngày 15 / 9 / 2023.\",\n" +
                    "\t\t\t\"time\": \"14: 45\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 16\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"unread\",\n" +
                    "\t\t\t\"category\": \"Hoạt động\"\n" +
                    "\t\t},\n" +
                    "\t\t{\n" +
                    "\t\t\t\"title\": \"THÔNG BÁO ĐÓNG HỌC PHÍ HỌC KỲ SUMMER 2023 \",\n" +
                    "\t\t\t\"description\": \"Phòng Kế toán thông báo sinh viên đóng học phí học kỳ Summer 2023 theo mức phí đã được công bố.Sinh viên có thể thanh toán qua ngân hàng hoặc trực tiếp tại phòng Kế toán.Thời hạn đóng học phí là ngày 31 / 7 / 2023.\",\n" +
                    "\t\t\t\"time\": \"10: 15\",\n" +
                    "\t\t\t\"date\": \"2023 - 07 - 16\",\n" +
                    "\t\t\t\"image\": \"https: //example.com/example.jpg\",\n" +
                    "\t\t\t\"status\": \"read\",\n" +
                    "\t\t\t\"category\": \"Học phí\"\n" +
                    "\t\t}\n" +
                    "\t]\n" +
                    "}";

            JSONObject jsonObject = new JSONObject(response);

            // Parse the JSON response
            JSONArray jsonNotifications = jsonObject.getJSONArray("notifications");

            // Create a list of Notification objects
            List<Notification> notifications = new ArrayList<>();
            for (int i = 0; i < jsonNotifications.length(); i++) {
                JSONObject jsonNotification = jsonNotifications.getJSONObject(i);
                String title = jsonNotification.getString("title");
                String description = jsonNotification.getString("description");
                String time = jsonNotification.getString("time");
                String date = jsonNotification.getString("date");
                String imageUrl = jsonNotification.getString("image");
                String status = jsonNotification.getString("status");
                String category = jsonNotification.getString("category");
                Notification notification = new Notification(title, description, time, date, imageUrl, status, category);
                notifications.add(notification);
            }

            // Parse the date and time strings into Date objects
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy - MM - dd HH: mm");
            for (Notification notification : notifications) {
                String dateTimeString = notification.date + " " + notification.time;
                Date dateTime = dateFormat.parse(dateTimeString);
                notification.dateTime = dateTime;
            }

            // Sort the notifications by date and time
            Collections.sort(notifications, new Comparator<Notification>() {
                @Override
                public int compare(Notification n1, Notification n2) {
                    return n2.dateTime.compareTo(n1.dateTime);
                }
            });


            // Group the notifications by date
            Map<String, List<Notification>> groupedNotifications = new HashMap<>();
            for (Notification notification : notifications) {
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
            SimpleDateFormat groupDateFormat = new SimpleDateFormat("yyyy - MM - dd");
            for (NotificationGroup group : notificationGroups) {
                Date date = groupDateFormat.parse(group.date);
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

        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
