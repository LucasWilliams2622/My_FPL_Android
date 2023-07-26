package com.example.myfplapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfplapplication.Model.ScheduleExam;
import com.example.myfplapplication.R;

import java.text.BreakIterator;
import java.util.List;

public class ScheduleExamAdapter extends RecyclerView.Adapter<ScheduleExamAdapter.ItemViewHolder> {
    private List<ScheduleExam> itemListScheduleExam;

    public ScheduleExamAdapter(List<ScheduleExam> itemListScheduleExam) {
        this.itemListScheduleExam = itemListScheduleExam;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_schedule_exam_coming, parent, false);
        return new ScheduleExamAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ScheduleExam item = itemListScheduleExam.get(position);

        holder.tv_code_exam.setText(item.getCodeSubject());
        holder.tv_name_exam.setText(item.getNameSubject());
        holder.tv_location_exam.setText(item.getLocation());
        holder.tv_time_exam.setText(item.getTime());
        holder.tv_date_exam.setText(item.getTime());
    }



    @Override
    public int getItemCount() {
        return itemListScheduleExam.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name_exam;
        TextView tv_code_exam;
        TextView tv_location_exam;
        TextView tv_time_exam;
        TextView tv_date_exam;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name_exam = itemView.findViewById(R.id.tv_name_exam);
            tv_code_exam = itemView.findViewById(R.id.tv_code_exam);
            tv_location_exam = itemView.findViewById(R.id.tv_location_exam);
            tv_time_exam = itemView.findViewById(R.id.tv_time_exam);
            tv_date_exam = itemView.findViewById(R.id.tv_date_exam);
        }
    }
}
