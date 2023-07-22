package com.example.myfplapplication.Views.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfplapplication.Adapter.NotificationAdapter;
import com.example.myfplapplication.Adapter.StudyAdapter;
import com.example.myfplapplication.Model.Notification;
import com.example.myfplapplication.Model.NotificationGroup;
import com.example.myfplapplication.Model.Study;
import com.example.myfplapplication.Model.StudyGroup;
import com.example.myfplapplication.R;
import com.example.myfplapplication.databinding.FragmentScheduleStudyBinding;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ScheduleStudyFragment extends Fragment {

    FragmentScheduleStudyBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScheduleStudyBinding.inflate(inflater, container, false);
        // Create a list of notification groups
        List<StudyGroup> studyGroups = new ArrayList<>();
        studyGroups.add(new StudyGroup("2023-07-19", Arrays.asList(
                new Study("MOB403", "Android Networking", "Ca 6", "T308 (Nhà T)", "19:30 - 21:30"),
                new Study("MOB401", "Lập trình Mobile đa nền tảng", "Ca 2", "T1108 (Nhà T)", "9:30 - 11:30")
        )));
        studyGroups.add(new StudyGroup("2023-07-20", Arrays.asList(
                new Study("MOB308", "Lập trình Game 2D", "Ca 2", "T1101 (Nhà T)", "9:30 - 11:30")
        )));
        studyGroups.add(new StudyGroup("2023-07-21", Arrays.asList(
                new Study("MOB306", "Khởi sự doanh nghiệp", "Ca 6", "T306 (Nhà T)", "19:30 - 21:30"),
                new Study("MOB402", "Lập trình Server cho Android", "Ca 6", "T1110 (Nhà T)", "19:30 - 21:30")
        )));

        // Create an adapter to populate the list with study groups
        StudyAdapter studyAdapter = new StudyAdapter(studyGroups);

        // Set the layout manager of the RecyclerView
        binding.rvStudy.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set the adapter of the RecyclerView
        binding.rvStudy.setAdapter(studyAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}