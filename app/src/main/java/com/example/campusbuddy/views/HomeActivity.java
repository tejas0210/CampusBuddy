package com.example.campusbuddy.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.example.campusbuddy.MainActivity;
import com.example.campusbuddy.R;
import com.example.campusbuddy.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#303030"));
        actionBar.setBackgroundDrawable(colorDrawable);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, FitnessCentreActivity.class));
            }
        });


//        SharedPreferences prefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
//        String lastActivity = prefs.getString("lastActivity", null);

//        if (lastActivity != null) {
//            try {
//                Class<?> activityClass = Class.forName(lastActivity);
//                Intent intent = new Intent(this, activityClass);
//                startActivity(intent);
//            } catch (ClassNotFoundException e) {
//
//            }
//        } else {
//            // If no last activity found, start default activity
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addService:
                startActivity(new Intent(this, NewServiceActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}