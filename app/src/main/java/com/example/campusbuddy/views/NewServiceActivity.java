package com.example.campusbuddy.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;


import com.example.campusbuddy.R;
import com.example.campusbuddy.databinding.ActivityNewServiceBinding;

public class NewServiceActivity extends AppCompatActivity implements View.OnClickListener{
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

        binding.btnGym.setOnClickListener(this::onClick);
        binding.btnInn.setOnClickListener(this::onClick);
        binding.btnTransport.setOnClickListener(this::onClick);
        binding.btnCafe.setOnClickListener(this::onClick);
        binding.btnPlay.setOnClickListener(this::onClick);
        binding.btnMess.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_Gym:
                startActivity(new Intent(NewServiceActivity.this, ServiceDetailsActivity.class));
                break;

            case R.id.btn_Inn:
                startActivity(new Intent(NewServiceActivity.this, ServiceDetailsActivity.class));
                break;

            case R.id.btn_Transport:
                startActivity(new Intent(NewServiceActivity.this, ServiceDetailsActivity.class));
                break;

            case R.id.btn_Cafe:
                startActivity(new Intent(NewServiceActivity.this, ServiceDetailsActivity.class));
                break;

            case R.id.btn_mess:
                startActivity(new Intent(NewServiceActivity.this, ServiceDetailsActivity.class));
                break;

            case R.id.btn_Play:
                startActivity(new Intent(NewServiceActivity.this, ServiceDetailsActivity.class));
                break;
        }
    }
}