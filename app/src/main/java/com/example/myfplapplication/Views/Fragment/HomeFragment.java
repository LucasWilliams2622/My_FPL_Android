package com.example.myfplapplication.Views.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfplapplication.Adapter.ItemNewsHomeAdapter;
import com.example.myfplapplication.Adapter.ScheduleStudyAdapter;
import com.example.myfplapplication.Model.ItemNewsHome;
import com.example.myfplapplication.Model.LichHoc;
import com.example.myfplapplication.Model.LoginInfo;
import com.example.myfplapplication.Model.ScheduleStudy;
import com.example.myfplapplication.R;
import com.example.myfplapplication.Service.APIService;
import com.example.myfplapplication.Service.UserService;
import com.example.myfplapplication.Views.Activities.LoginActivity;
import com.example.myfplapplication.Views.Activities.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    int MAX_SCHEDULE_SIZE = 2;
    private RecyclerView recyclerViewNews,recyclerViewScheduleStudy;
    private ItemNewsHomeAdapter itemNewsHome;
    private ScheduleStudyAdapter scheduleStudyAdapter;
    public HomeFragment() {
        // Required empty public constructor
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
        ArrayList<ScheduleStudy> itemListScheduleStudy = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView myTextView = view.findViewById(R.id.myTextView);
        myTextView.setTypeface(null, Typeface.BOLD); // Đặt chữ in đậm
        TextView myTextView2 = view.findViewById(R.id.myTextView2);
        UserService userService = new UserService(getContext());
        myTextView2.setText(userService.getName());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIService.base_link)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService apiService = retrofit.create(APIService.class);

        Call<ArrayList<LichHoc>> response = apiService.GetLichHoc(userService.getToken());
        response.enqueue(new Callback<ArrayList<LichHoc>>() {
            @Override
            public void onResponse(Call<ArrayList<LichHoc>> call, Response<ArrayList<LichHoc>> response) {
                ArrayList<LichHoc> dsLichHoc = response.body();
                for(int i = 0; i < dsLichHoc.size(); i++){
                    if(i > MAX_SCHEDULE_SIZE - 1) break;
                    LichHoc lichHoc = dsLichHoc.get(i);
                    itemListScheduleStudy.add(new ScheduleStudy(lichHoc.ten_mon_hoc, lichHoc.mon_hoc_id, lichHoc.dia_diem, lichHoc.getCaHoc()));
                }
                scheduleStudyAdapter = new ScheduleStudyAdapter(itemListScheduleStudy);

                recyclerViewScheduleStudy.setAdapter(scheduleStudyAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<LichHoc>> call, Throwable t) {
                Toast.makeText(getContext(), "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewNews = view.findViewById(R.id.recycler_news_list);
        recyclerViewScheduleStudy = view.findViewById(R.id.recycler_schedule_study_today);

        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewScheduleStudy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<ItemNewsHome> itemList = new ArrayList<>();
        itemList.add(new ItemNewsHome("Title 1", "Content 1Content 1Content 1Content 1Content 1Content 1", R.drawable.ic_launcher_background));
        itemList.add(new ItemNewsHome("Title 2", "Content 2", R.drawable.ic_launcher_background));
        itemList.add(new ItemNewsHome("Title 3", "Content 3", R.drawable.ic_launcher_background));
        itemNewsHome = new ItemNewsHomeAdapter(itemList);
        recyclerViewNews.setAdapter(itemNewsHome);
        return view;
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}