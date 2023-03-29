package com.example.campusbuddy.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.campusbuddy.MainActivity;
import com.example.campusbuddy.R;
import com.example.campusbuddy.databinding.ActivityHomeBinding;
import com.example.campusbuddy.databinding.ActivityServiceDetailsBinding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, LocationListener {

    ActivityHomeBinding binding;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;
    private static final int READ_PERMISSION = 101;
    String latitude, longitude, locationToSet;

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

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            checkLocationEnabledOrNot();
        } else {
            getLocation();
        }


        if (ContextCompat.checkSelfPermission(HomeActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PERMISSION);
        }

        checkLocationEnabledOrNot();
        getLocation();
    }

//

    private void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 5, this);
            //LocationManager.NETWORK_PROVIDER, 500, 5, this

        }
        catch (SecurityException e){
            e.printStackTrace();
        }
    }

    private void checkLocationEnabledOrNot() {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = false;
        boolean networkEnabled = false;

        try {
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        try {
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        if(!gpsEnabled && !networkEnabled){
            new AlertDialog.Builder(HomeActivity.this).setTitle("Enable GPS Service").setCancelable(false).setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }).setNegativeButton("Cancel",null).show();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fitness:
                startActivity(new Intent(HomeActivity.this, FitnessCentreActivity.class));
                break;
            case R.id.Inn:
                startActivity(new Intent(HomeActivity.this, InnActivity.class));
                break;
            case R.id.transport:
                startActivity(new Intent(HomeActivity.this, TransportActivity.class));
                break;
            case R.id.food:
                startActivity(new Intent(HomeActivity.this, FoodActivity.class));
                break;
            case R.id.newOpenings:
                startActivity(new Intent(HomeActivity.this, NewOpeningsActivity.class));
                break;
            case R.id.lifestyle:
                startActivity(new Intent(HomeActivity.this, LifestyleActivity.class));
                break;
            case R.id.playArea:
                startActivity(new Intent(HomeActivity.this, PlayAreaActivity.class));
                break;
            case R.id.collegeClubs:
                startActivity(new Intent(HomeActivity.this, CollegeClubsActivity.class));
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
                Intent intent = new Intent(HomeActivity.this,ServiceDetailsActivity.class);
                intent.putExtra("LocationToSet",locationToSet);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);

            locationToSet = addresses.get(0).getSubLocality()+", "+addresses.get(0).getLocality()+" - "+addresses.get(0).getPostalCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {
        LocationListener.super.onLocationChanged(locations);
    }

    @Override
    public void onFlushComplete(int requestCode) {
        LocationListener.super.onFlushComplete(requestCode);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }
}