package com.example.campusbuddy.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.campusbuddy.R;
import com.example.campusbuddy.adapters.MyPagerAdapter;
import com.example.campusbuddy.databinding.ActivityFitnessDataBinding;
import com.example.campusbuddy.fragments.DetailsFragment;
import com.google.android.material.tabs.TabLayout;

public class Fitness_Data_Activity extends AppCompatActivity {
    ActivityFitnessDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFitnessDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        Bundle bundle = new Bundle();
        bundle.putString("serviceType", getIntent().getStringExtra("serviceType"));
        bundle.putString("serviceTypeName", getIntent().getStringExtra("serviceTypeName"));
// set MyFragment Arguments
        DetailsFragment myFragment = new DetailsFragment();
        myFragment.setArguments(bundle);


        getSupportActionBar().setTitle(getIntent().getStringExtra("serviceTypeName")+ getIntent().getStringExtra("serviceType"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Details"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("About"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Photos"));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final MyPagerAdapter adapter = new MyPagerAdapter(this,getSupportFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(adapter);

        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}