package com.example.campusbuddy.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.campusbuddy.R;
import com.example.campusbuddy.adapters.MyPagerAdapter;
import com.example.campusbuddy.databinding.ActivityDetailsBinding;
import com.google.android.material.tabs.TabLayout;

public class DetailsActivity extends AppCompatActivity {
    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = DetailsActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(DetailsActivity.this, R.color.arsenic));
        // assigning ID of the toolbar to a variable
        Toolbar toolbar = findViewById(R.id.toolbar);
        // using toolbar as ActionBar
        setSupportActionBar(toolbar);
        setTitle("Fitness Centres");

//        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(this);
//        binding.viewPager.setAdapter(myPagerAdapter);


//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Details"));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("About"));
//        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Photos"));
//        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
        final MyPagerAdapter adapter = new MyPagerAdapter(this,getSupportFragmentManager(), binding.tabLayout.getTabCount());
        binding.viewPager.setAdapter(adapter);
//
//        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));
//
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

        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.getTabAt(position).select();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}