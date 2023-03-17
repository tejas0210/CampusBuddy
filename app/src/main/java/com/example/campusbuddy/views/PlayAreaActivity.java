package com.example.campusbuddy.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.campusbuddy.databinding.ActivityInnBinding;

public class PlayAreaActivity extends AppCompatActivity {
    ActivityInnBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#303030"));
        actionBar.setBackgroundDrawable(colorDrawable);
        setTitle("Play Area");
        binding = ActivityInnBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}