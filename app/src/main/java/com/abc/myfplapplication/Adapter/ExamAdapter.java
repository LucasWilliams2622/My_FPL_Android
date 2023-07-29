package com.abc.myfplapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

<<<<<<< Updated upstream:app/src/main/java/com/abc/myfplapplication/Adapter/ExamAdapter.java
import com.abc.myfplapplication.Model.Study;
import com.abc.myfplapplication.Model.StudyGroup;
import com.abc.myfplapplication.R;

=======
import com.abc.myfplapplication.R;
import com.example.myfplapplication.Model.Study;
import com.example.myfplapplication.Model.StudyGroup;
>>>>>>> Stashed changes:app/src/main/java/com/example/myfplapplication/Adapter/ExamAdapter.java

import java.util.List;

public class ExamAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_DATE = 0;
    private static final int VIEW_TYPE_NOTIFICATION = 1;

    private List<StudyGroup> studyGroups;

    public ExamAdapter(List<StudyGroup> studyGroups) {
        this.studyGroups = studyGroups;
    }

    @Override
    public int getItemViewType(int position) {
        int count = 0;
        for (StudyGroup group: studyGroups) {
            if (position == count) {
                return VIEW_TYPE_DATE;
            }
            count++;
            if (position < count + group.studies.size()) {
                return VIEW_TYPE_NOTIFICATION;
            }
            count += group.studies.size();
        }
        throw new IllegalArgumentException("Invalid position");
    }

//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        if (viewType == VIEW_TYPE_DATE) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_subject_date, parent, false);
//            return new ExamAdapter.DateViewHolder(view);
//        } else if (viewType == VIEW_TYPE_NOTIFICATION) {
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.item_subject, parent, false);
//            return new ExamAdapter(view);
//        } else {
//            throw new IllegalArgumentException("Invalid view type");
//        }
//    }
//
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DATE) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_subject_date, parent, false);
            return new DateViewHolder(view);
        } else if (viewType == VIEW_TYPE_NOTIFICATION) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_subject, parent, false);
            return new ExamAdapter.ExamViewHolder(view);
        } else {
            throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int count = 0;
        for (StudyGroup group: studyGroups) {
            if (position == count) {
                ((DateViewHolder) holder).bind(group.date);
                return;
            }
            count++;
            if (position < count + group.studies.size()) {
                ((ExamViewHolder) holder).bind(group.studies.get(position - count));
                return;
            }
            count += group.studies.size();
        }
        throw new IllegalArgumentException("Invalid position");
    }

    /*
    *
    *   @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int count = 0;
        for (StudyGroup group: studyGroups) {
            if (position == count) {
                ((DateViewHolder) holder).bind(group.date);
                return;
            }
            count++;
            if (position < count + group.studies.size()) {
                ((StudyViewHolder) holder).bind(group.studies.get(position - count));
                return;
            }
            count += group.studies.size();
        }
        throw new IllegalArgumentException("Invalid position");
    }*/
    @Override
    public int getItemCount() {
        int count = 0;
        for (StudyGroup group: studyGroups) {
            count += 1 + group.studies.size();
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

    private static class ExamViewHolder extends RecyclerView.ViewHolder {
        private TextView mon_hoc_id, ten_mon_hoc, dia_diem, ca, ngay_hoc;
        private View view;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            mon_hoc_id = itemView.findViewById(R.id.mon_hoc_id);
            ten_mon_hoc = itemView.findViewById(R.id.ten_mon_hoc);
            dia_diem = itemView.findViewById(R.id.dia_diem);
            ca = itemView.findViewById(R.id.ca);
            ngay_hoc = itemView.findViewById(R.id.ngay_hoc);
            view = itemView.findViewById(R.id.view);
        }

        public void bind(Study study) {
            mon_hoc_id.setText(study.mon_hoc_id);
            ten_mon_hoc.setText(study.ten_mon_hoc);
            dia_diem.setText(study.dia_diem);
            ca.setText(study.ca);
            ngay_hoc.setText(study.ngay_hoc);
            view.setBackgroundColor(Integer.parseInt(study.viewColor));

            // Load the image using your preferred image loading library
        }
    }
}
