package com.abc.myfplapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< Updated upstream:app/src/main/java/com/abc/myfplapplication/Adapter/NotificationAdapter.java
import com.abc.myfplapplication.Model.Notification;
import com.abc.myfplapplication.Model.NotificationGroup;
import com.abc.myfplapplication.R;
=======
import com.abc.myfplapplication.R;
import com.example.myfplapplication.Model.Notification;
import com.example.myfplapplication.Model.NotificationGroup;
>>>>>>> Stashed changes:app/src/main/java/com/example/myfplapplication/Adapter/NotificationAdapter.java


import java.text.SimpleDateFormat;
import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Notification notification);
    }

    private OnItemClickListener onItemClickListener;


    private static final int VIEW_TYPE_DATE = 0;
    private static final int VIEW_TYPE_NOTIFICATION = 1;

    private List<NotificationGroup> notificationGroups;

    public NotificationAdapter(List<NotificationGroup> notificationGroups) {
        this.notificationGroups = notificationGroups;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemViewType(int position) {
        int count = 0;
        for (NotificationGroup group : notificationGroups) {
            if (position == count) {
                return VIEW_TYPE_DATE;
            }
            count++;
            if (position < count + group.notifications.size()) {
                return VIEW_TYPE_NOTIFICATION;
            }
            count += group.notifications.size();
        }
        throw new IllegalArgumentException("Invalid position");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DATE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_notification_date, parent, false);
            return new DateViewHolder(view);
        } else if (viewType == VIEW_TYPE_NOTIFICATION) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_notification, parent, false);
            return new NotificationViewHolder(view);
        } else {
            throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int count = 0;
        for (NotificationGroup group : notificationGroups) {
            if (position == count) {
                ((DateViewHolder) holder).bind(group.date);
                return;
            }
            count++;
            if (position < count + group.notifications.size()) {
                Notification notification = group.notifications.get(position - count);
                ((NotificationViewHolder) holder).bind(notification);

                // Set an OnClickListener on the root view of the item view holder
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(notification);
                        }
                    }
                });

                return;
            }
            count += group.notifications.size();
        }

        throw new IllegalArgumentException("Invalid position");
    }


    @Override
    public int getItemCount() {
        int count = 0;
        for (NotificationGroup group : notificationGroups) {
            count += 1 + group.notifications.size();
        }
        return count;
    }

    private static class DateViewHolder extends RecyclerView.ViewHolder {
        private TextView dateView;

        public DateViewHolder(@NonNull View itemView) {
            super(itemView);
            dateView = itemView.findViewById(R.id.date);
        }

        public void bind(String date) {
            dateView.setText(date);
        }
    }

    private static class NotificationViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView descriptionView;
        private TextView timeView;
        private ImageView imageView;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            descriptionView = itemView.findViewById(R.id.description);
            timeView = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.image);
        }

        public void bind(Notification notification) {
            titleView.setText(notification.title);
            descriptionView.setText(notification.description);
            timeView.setText(new SimpleDateFormat("HH:mm").format(notification.dateTime));
            // Load the image using your preferred image loading library
        }
    }
}

