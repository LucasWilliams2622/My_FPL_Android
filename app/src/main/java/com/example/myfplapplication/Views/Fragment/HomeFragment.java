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
import com.example.myfplapplication.Model.ItemNewsHome;
import com.example.myfplapplication.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private ItemNewsHomeAdapter itemNewsHome;

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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        TextView myTextView = view.findViewById(R.id.myTextView);
        myTextView.setTypeface(null, Typeface.BOLD);

        recyclerView = view.findViewById(R.id.recycler_news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // Thêm các item vào danh sách itemList


        ArrayList<ItemNewsHome> itemList = new ArrayList<>();
        itemList.add(new ItemNewsHome("Title 1", "Content 1Content 1Content 1Content 1Content 1Content 1", R.drawable.ic_launcher_background));
        itemList.add(new ItemNewsHome("Title 2", "Content 2", R.drawable.ic_launcher_background));
        itemList.add(new ItemNewsHome("Title 3", "Content 3", R.drawable.ic_launcher_background));

        itemNewsHome = new ItemNewsHomeAdapter(itemList);
        recyclerView.setAdapter(itemNewsHome);

        return view;
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}