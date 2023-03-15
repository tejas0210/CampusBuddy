package com.example.campusbuddy.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;


import com.example.campusbuddy.databinding.ActivityNewServiceBinding;

public class NewServiceActivity extends AppCompatActivity {
    ActivityNewServiceBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewServiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Add Your Service");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#303030"));
        actionBar.setBackgroundDrawable(colorDrawable);

        binding.btnGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NewServiceActivity.this, ServiceDetailsActivity.class));
            }
        });
    }
}