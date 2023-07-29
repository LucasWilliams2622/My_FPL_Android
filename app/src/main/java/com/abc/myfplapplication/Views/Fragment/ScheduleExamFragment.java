package com.abc.myfplapplication.Views.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

<<<<<<< Updated upstream:app/src/main/java/com/abc/myfplapplication/Views/Fragment/ScheduleExamFragment.java
import com.abc.myfplapplication.Adapter.ExamAdapter;
import com.abc.myfplapplication.Model.Study;
import com.abc.myfplapplication.Model.StudyGroup;
import com.abc.myfplapplication.databinding.FragmentScheduleExamBinding;

=======
import com.abc.myfplapplication.databinding.FragmentScheduleExamBinding;
import com.example.myfplapplication.Adapter.ExamAdapter;
import com.example.myfplapplication.Adapter.StudyAdapter;
import com.example.myfplapplication.Model.Study;
import com.example.myfplapplication.Model.StudyGroup;
>>>>>>> Stashed changes:app/src/main/java/com/example/myfplapplication/Views/Fragment/ScheduleExamFragment.java

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ScheduleExamFragment extends Fragment {

//    FragmentScheduleExamBinding binding;
    FragmentScheduleExamBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScheduleExamBinding.inflate(inflater, container, false);
        // Create a list of notification groups
        List<StudyGroup> studyGroups = new ArrayList<>();
        studyGroups.add(new StudyGroup("2023-07-19", Arrays.asList(
                new Study("MOB403", "Android Networking", "Ca 6", "T308 (Nhà T)", "19:30 - 21:30", Color.parseColor("#FDB196"))
        )));
        studyGroups.add(new StudyGroup("2023-07-20", Arrays.asList(
                new Study("MOB308", "Lập trình Game 2D", "Ca 2", "T1101 (Nhà T)", "9:30 - 11:30",Color.parseColor("#FBEAC8"))
        )));
        studyGroups.add(new StudyGroup("2023-07-21", Arrays.asList(
                new Study("MOB306", "Khởi sự doanh nghiệp", "Ca 6", "T306 (Nhà T)", "19:30 - 21:30",Color.parseColor("#B1D9CD"))
        )));

        // Create an adapter to populate the list with study groups
        //StudyAdapter studyAdapter = new StudyAdapter(studyGroups);

        ExamAdapter examAdapter = new ExamAdapter(studyGroups);
        // Set the layout manager of the RecyclerView
        binding.rvExam.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set the adapter of the RecyclerView
        binding.rvExam.setAdapter(examAdapter);

        return binding.getRoot();
    }
}