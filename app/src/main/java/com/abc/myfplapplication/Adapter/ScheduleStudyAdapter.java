package com.abc.myfplapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abc.myfplapplication.Model.ScheduleStudy;
import com.abc.myfplapplication.R;


import java.util.List;

public class ScheduleStudyAdapter extends RecyclerView.Adapter<ScheduleStudyAdapter.ItemViewHolder> {

    private List<ScheduleStudy> itemListScheduleStudy;

    public ScheduleStudyAdapter(List<ScheduleStudy> itemListScheduleStudy) {
        this.itemListScheduleStudy = itemListScheduleStudy;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_first_schedule_study_today, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ScheduleStudy item = itemListScheduleStudy.get(position);

        holder.tv_code_subject.setText(item.getCodeSubject());
        holder.tv_name_subject.setText(item.getNameSubject());
        holder.tv_location.setText(item.getLocation());
        holder.tv_time.setText(item.getTime());
//        holder.tv_date.setText((CharSequence) item.getDateSchedule());

    }


    @Override
    public int getItemCount() {
        return itemListScheduleStudy.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name_subject;
        TextView tv_code_subject;
        TextView tv_location;
        TextView tv_time;
        TextView tv_date;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_subject = itemView.findViewById(R.id.tv_name_subject);
            tv_code_subject = itemView.findViewById(R.id.tv_code_subject);
            tv_location = itemView.findViewById(R.id.tv_location);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
