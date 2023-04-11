package com.example.guiderunner2;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.guiderunner2.databinding.ActivityBottomNavBinding;

public class BottomNav extends AppCompatActivity {


    ActivityBottomNavBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBottomNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new NewsFragment());

        binding.BottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

                case R.id.News:
                    replaceFragment(new NewsFragment());
                    break;
                case R.id.Gallery:
                    replaceFragment(new GameLibraryFragment());
                    break;
                case R.id.Profile:
                    replaceFragment(new ProfileFragment());
                    break;

            }


            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.Frame_Layout,fragment);
        fragmentTransaction.commit();
    }
}