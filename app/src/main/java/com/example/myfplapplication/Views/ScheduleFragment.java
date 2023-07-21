package com.example.myfplapplication.Views;

import android.content.res.ObbInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfplapplication.Adapter.FragmentScheduleAdapter;
import com.example.myfplapplication.R;
import com.example.myfplapplication.databinding.FragmentScheduleBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ScheduleFragment extends Fragment {


    FragmentScheduleBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        FragmentScheduleAdapter adapter = new FragmentScheduleAdapter(getChildFragmentManager(), getLifecycle());
        binding.vpSchedule.setAdapter(adapter);

        new TabLayoutMediator(binding.mTabSchedule, binding.vpSchedule, ((tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Lịch Học");
                    break;
                case 1:
                    tab.setText("Lịch Thi");
                    break;
            }
        })).attach();

        binding.mTabSchedule.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpSchedule.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.vpSchedule.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.mTabSchedule.selectTab(binding.mTabSchedule.getTabAt(position));
            }
        });

        return binding.getRoot();
    }
}