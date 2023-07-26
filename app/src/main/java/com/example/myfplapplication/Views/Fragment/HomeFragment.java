package com.example.myfplapplication.Views.Fragment;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfplapplication.Adapter.ItemNewsHomeAdapter;
import com.example.myfplapplication.Adapter.ScheduleExamAdapter;
import com.example.myfplapplication.Adapter.ScheduleStudyAdapter;
import com.example.myfplapplication.Model.ItemNewsHome;
import com.example.myfplapplication.Model.ScheduleExam;
import com.example.myfplapplication.Model.ScheduleStudy;
import com.example.myfplapplication.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewNews,recyclerViewScheduleStudy,recyclerViewScheduleExam;
    private ItemNewsHomeAdapter itemNewsHome;
    private ScheduleStudyAdapter scheduleStudyAdapter;
    private  ScheduleExamAdapter scheduleExamAdapter;
    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView myTextView = view.findViewById(R.id.myTextView);
        myTextView.setTypeface(null, Typeface.BOLD); // Đặt chữ in đậm
        recyclerViewNews = view.findViewById(R.id.recycler_news_list);
        recyclerViewScheduleStudy = view.findViewById(R.id.recycler_schedule_study_today);
        recyclerViewScheduleExam = view.findViewById(R.id.recycler_schedule_exam_coming);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewScheduleStudy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewScheduleExam.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<ItemNewsHome> itemList = new ArrayList<>();
        itemList.add(new ItemNewsHome("Title 1", "Content 1Content 1Content 1Content 1Content 1Content 1", R.drawable.ic_launcher_background));
        itemList.add(new ItemNewsHome("Title 2", "Content 2", R.drawable.ic_launcher_background));
        itemList.add(new ItemNewsHome("Title 3", "Content 3", R.drawable.ic_launcher_background));
        itemNewsHome = new ItemNewsHomeAdapter(itemList);
        recyclerViewNews.setAdapter(itemNewsHome);

        ArrayList<ScheduleStudy> itemListScheduleStudy = new ArrayList<>();
        itemListScheduleStudy.add(new ScheduleStudy("Lập trình Android nâng cao", "MOB123","Phòng T302 (Tòa T)","Ca 6 19:30 -21:30"));
        itemListScheduleStudy.add(new ScheduleStudy("Lập trình Android cơ bản", "MOB123","Phòng T302 (Tòa T)","Ca 6 19:30 -21:30"));
        scheduleStudyAdapter = new ScheduleStudyAdapter(itemListScheduleStudy);
        recyclerViewScheduleStudy.setAdapter(scheduleStudyAdapter);

        ArrayList<ScheduleExam> itemListScheduleExam = new ArrayList<>();
        itemListScheduleExam.add(new ScheduleExam("Lập trình Android nâng cao", "MOB123","Phòng T302 (Tòa T)","Thứ 7 12/12/2012","Ca 6 19:30 -21:30"));
        itemListScheduleExam.add(new ScheduleExam("Lập trình Android cơ bản", "MOB123","Phòng T302 (Tòa T)","Thứ 7 12/12/2012","Ca 6 19:30 -21:30"));
        scheduleExamAdapter = new ScheduleExamAdapter(itemListScheduleExam);
        recyclerViewScheduleExam.setAdapter(scheduleExamAdapter);

        return view;
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}