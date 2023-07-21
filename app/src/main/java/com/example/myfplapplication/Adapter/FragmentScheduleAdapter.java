package com.example.myfplapplication.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myfplapplication.Views.Fragment.ScheduleExamFragment;
import com.example.myfplapplication.Views.Fragment.ScheduleStudyFragment;

public class FragmentScheduleAdapter extends FragmentStateAdapter{


    public FragmentScheduleAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ScheduleStudyFragment();
            case 1:
                return new ScheduleExamFragment();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
