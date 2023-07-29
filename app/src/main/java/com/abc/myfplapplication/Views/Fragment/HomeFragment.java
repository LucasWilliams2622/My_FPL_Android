package com.abc.myfplapplication.Views.Fragment;

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

import com.abc.myfplapplication.Adapter.ItemNewsHomeAdapter;
import com.abc.myfplapplication.Adapter.ScheduleExamAdapter;
import com.abc.myfplapplication.Adapter.ScheduleStudyAdapter;
import com.abc.myfplapplication.Model.ItemNewsHome;
import com.abc.myfplapplication.Model.ScheduleExam;
import com.abc.myfplapplication.Model.LichHoc;
import com.abc.myfplapplication.Model.ScheduleStudy;
import com.abc.myfplapplication.R;
import com.abc.myfplapplication.Service.APIService;
import com.abc.myfplapplication.Service.UserService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    int MAX_SCHEDULE_SIZE = 2;

    private RecyclerView recyclerViewNews,recyclerViewScheduleStudy,recyclerViewScheduleExam;
    private ItemNewsHomeAdapter itemNewsHome;
    private ScheduleStudyAdapter scheduleStudyAdapter;
    private  ScheduleExamAdapter scheduleExamAdapter;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APIService.base_link)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIService apiService = retrofit.create(APIService.class);
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
        TextView myTextView2 = view.findViewById(R.id.myTextView2);
        UserService userService = new UserService(getContext());
        myTextView2.setText(userService.getName());

        recyclerViewNews = view.findViewById(R.id.recycler_news_list);
        recyclerViewScheduleStudy = view.findViewById(R.id.recycler_schedule_study_today);
        recyclerViewScheduleExam = view.findViewById(R.id.recycler_schedule_exam_coming);
        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewScheduleStudy.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewScheduleExam.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        ArrayList<ScheduleExam> itemListScheduleExam = new ArrayList<>();
        itemListScheduleExam.add(new ScheduleExam("Lập trình Android nâng cao", "MOB123","Phòng T302 (Tòa T)","Thứ 7 12/12/2012","Ca 6 19:30 -21:30"));
        itemListScheduleExam.add(new ScheduleExam("Lập trình Android cơ bản", "MOB123","Phòng T302 (Tòa T)","Thứ 7 12/12/2012","Ca 6 19:30 -21:30"));
        scheduleExamAdapter = new ScheduleExamAdapter(itemListScheduleExam);
        recyclerViewScheduleExam.setAdapter(scheduleExamAdapter);
        fetchData();
        return view;
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    public void fetchData() {
        ArrayList<ScheduleStudy> itemListScheduleStudy = new ArrayList<>();
        UserService userService = new UserService(getContext());
        Call<ArrayList<LichHoc>> response = apiService.GetLichHocHomNay(userService.getToken());
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

        Call<ArrayList<Notification>> getNews = apiService.GetNews(userService.getToken());
        getNews.enqueue(new Callback<ArrayList<Notification>>() {
            @Override
            public void onResponse(Call<ArrayList<Notification>> call, Response<ArrayList<Notification>> response) {
                ArrayList<Notification> dsNews = response.body();
                ArrayList<ItemNewsHome> itemList = new ArrayList<>();
                for(int i = 0; i < dsNews.size(); i++){
                    if(i > MAX_SCHEDULE_SIZE - 1) break;
                    Notification news = dsNews.get(i);
                    itemList.add(new ItemNewsHome(news.title, news.description, news.imageUrl));
                }
                itemNewsHome = new ItemNewsHomeAdapter(itemList);
                recyclerViewNews.setAdapter(itemNewsHome);
            }

            @Override
            public void onFailure(Call<ArrayList<Notification>> call, Throwable t) {
                Toast.makeText(getContext(), "Không thể lấy dữ liệu", Toast.LENGTH_SHORT).show();
            }
        });
    }
}