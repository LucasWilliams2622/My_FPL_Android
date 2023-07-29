package com.abc.myfplapplication.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

<<<<<<< Updated upstream:app/src/main/java/com/abc/myfplapplication/Views/Activities/MainActivity.java
import com.abc.myfplapplication.R;
import com.abc.myfplapplication.Views.Fragment.HomeFragment;
import com.abc.myfplapplication.Views.Fragment.NotificationFragment;
import com.abc.myfplapplication.Views.Fragment.ProfileFragment;
import com.abc.myfplapplication.Views.Fragment.ScheduleFragment;
import com.abc.myfplapplication.databinding.ActivityMainBinding;
=======

import com.abc.myfplapplication.R;
import com.abc.myfplapplication.databinding.ActivityMainBinding;
import com.example.myfplapplication.Service.UserService;
import com.example.myfplapplication.Views.Fragment.HomeFragment;
import com.example.myfplapplication.Views.Fragment.NotificationFragment;
import com.example.myfplapplication.Views.Fragment.ProfileFragment;
import com.example.myfplapplication.Views.Fragment.ScheduleFragment;
>>>>>>> Stashed changes:app/src/main/java/com/example/myfplapplication/Views/Activities/MainActivity.java


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
                case R.id.schedule:
                    replaceFragment(new ScheduleFragment());
                    break;
                case R.id.notification:
                    replaceFragment(new NotificationFragment());
                    break;
            }
            return true;
        });

        UserService userService = new UserService(MainActivity.this);

        if(userService.getToken() == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}