package com.example.campusbuddy.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.campusbuddy.MainActivity;
import com.example.campusbuddy.R;
import com.example.campusbuddy.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //For status bar color
        Window window = HomeActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(HomeActivity.this, R.color.white));

        // For making our toolbar as default action bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Campus Buddy");



        binding.fitness.setOnClickListener(this::onClick);
        binding.Inn.setOnClickListener(this::onClick);
        binding.transport.setOnClickListener(this::onClick);
        binding.collegeClubs.setOnClickListener(this::onClick);
        binding.playArea.setOnClickListener(this::onClick);
        binding.food.setOnClickListener(this::onClick);
        binding.lifestyle.setOnClickListener(this::onClick);
        binding.newOpenings.setOnClickListener(this::onClick);



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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fitness:
                startActivity(new Intent(HomeActivity.this,FitnessCentreActivity.class));
                break;
            case R.id.Inn:
                startActivity(new Intent(HomeActivity.this,InnActivity.class));
                break;
            case R.id.transport:
                startActivity(new Intent(HomeActivity.this,TransportActivity.class));
                break;
            case R.id.food:
                startActivity(new Intent(HomeActivity.this,FoodActivity.class));
                break;
            case R.id.newOpenings:
                startActivity(new Intent(HomeActivity.this,NewOpeningsActivity.class));
                break;
            case R.id.lifestyle:
                startActivity(new Intent(HomeActivity.this,LifestyleActivity.class));
                break;
            case R.id.playArea:
                startActivity(new Intent(HomeActivity.this,PlayAreaActivity.class));
                break;
            case R.id.collegeClubs:
                startActivity(new Intent(HomeActivity.this,CollegeClubsActivity.class));
                break;

        }

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