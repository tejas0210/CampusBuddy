package com.example.campusbuddy.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.campusbuddy.databinding.ActivityInnBinding;

public class InnActivity extends AppCompatActivity {

    ActivityInnBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInnBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}