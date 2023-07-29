package com.abc.myfplapplication.Views.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.abc.myfplapplication.Adapter.ItemNewsHomeAdapter;
import com.abc.myfplapplication.Adapter.NotificationAdapter;
import com.abc.myfplapplication.Adapter.ScheduleStudyAdapter;
import com.abc.myfplapplication.Adapter.StudyAdapter;
import com.abc.myfplapplication.Model.ItemNewsHome;
import com.abc.myfplapplication.Model.LichHoc;
import com.abc.myfplapplication.Model.News;
import com.abc.myfplapplication.Model.Notification;
import com.abc.myfplapplication.Model.NotificationGroup;
import com.abc.myfplapplication.Model.ScheduleStudy;
import com.abc.myfplapplication.Model.Study;
import com.abc.myfplapplication.Model.StudyGroup;
import com.abc.myfplapplication.R;
import com.abc.myfplapplication.Service.APIService;
import com.abc.myfplapplication.Service.UserService;
import com.abc.myfplapplication.databinding.FragmentScheduleStudyBinding;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ScheduleStudyFragment extends Fragment {

    FragmentScheduleStudyBinding binding;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIService.base_link)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService apiService = retrofit.create(APIService.class);
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScheduleStudyBinding.inflate(inflater, container, false);
        // Create a list of notification groups




        // Set the layout manager of the RecyclerView
        binding.rvStudy.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set the adapter of the RecyclerView

        fetchData();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public int getViewColor(int index) {
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#FDB196"));
        colors.add(Color.parseColor("#FAD2A7"));
        colors.add(Color.parseColor("#FBEAC8"));
        colors.add(Color.parseColor("#B1D9CD"));
        colors.add(Color.parseColor("#93C2C6"));
        return colors.get(index % colors.size());
    }

    public void fetchData() {
        ArrayList<ScheduleStudy> itemListScheduleStudy = new ArrayList<>();
        UserService userService = new UserService(getContext());
        Call<ArrayList<LichHoc>> response = apiService.GetLichHoc(userService.getToken());
        response.enqueue(new Callback<ArrayList<LichHoc>>() {
            @Override
            public void onResponse(Call<ArrayList<LichHoc>> call, Response<ArrayList<LichHoc>> response) {
                ArrayList<LichHoc> dsLichHoc = response.body();
                List<StudyGroup> studyGroups = new ArrayList<>();
                TreeMap<String, ArrayList<Study>> studyGroupsHMap = new TreeMap<String, ArrayList<Study>>();
                int studyGroupsIndex = 0;
                for(int i = 0; i < dsLichHoc.size(); i++){
                    LichHoc lichHoc = dsLichHoc.get(i);
                    Study study = new Study(lichHoc.mon_hoc_id, lichHoc.ten_mon_hoc, "Ca " + lichHoc.ca, lichHoc.dia_diem, lichHoc.getCaHocTime(), getViewColor(studyGroupsIndex));
                    if (!studyGroupsHMap.containsKey(lichHoc.ngay_hoc)) {
                        ArrayList<Study> list = new ArrayList<Study>();
                        list.add(study);

                        studyGroupsHMap.put(lichHoc.ngay_hoc, list);
                        studyGroupsIndex++;
                    } else {
                        studyGroupsHMap.get(lichHoc.ngay_hoc).add(study);
                    }
                }
                studyGroupsHMap.forEach((key,value) -> studyGroups.add(new StudyGroup(key, value)));

//                studyGroups.add(new StudyGroup("2023-07-19", Arrays.asList(
//                        new Study("MOB403", "Android Networking", "Ca 6", "T308 (Nhà T)", "19:30 - 21:30", Color.parseColor("#FDB196")),
//                        new Study("MOB401", "Lập trình Mobile đa nền tảng", "Ca 2", "T1108 (Nhà T)", "9:30 - 11:30",Color.parseColor("#FAD2A7"))
//                )));
//                studyGroups.add(new StudyGroup("2023-07-20", Arrays.asList(
//                        new Study("MOB308", "Lập trình Game 2D", "Ca 2", "T1101 (Nhà T)", "9:30 - 11:30",Color.parseColor("#FBEAC8"))
//                )));
//                studyGroups.add(new StudyGroup("2023-07-21", Arrays.asList(
//                        new Study("MOB306", "Khởi sự doanh nghiệp", "Ca 6", "T306 (Nhà T)", "19:30 - 21:30",Color.parseColor("#B1D9CD")),
//                        new Study("MOB402", "Lập trình Server cho Android", "Ca 6", "T1110 (Nhà T)", "19:30 - 21:30",Color.parseColor("#93C2C6"))
//                )));
                // Create an adapter to populate the list with study groups
                StudyAdapter studyAdapter = new StudyAdapter(studyGroups);

                binding.rvStudy.setAdapter(studyAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<LichHoc>> call, Throwable t) {
                Toast.makeText(getContext(), "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}