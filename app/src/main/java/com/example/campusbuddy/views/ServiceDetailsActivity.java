package com.example.campusbuddy.views;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.campusbuddy.adapters.ImageAdapter;
import com.example.campusbuddy.databinding.ActivityServiceDetailsBinding;
import com.example.campusbuddy.model.ServiceModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ServiceDetailsActivity extends AppCompatActivity {
    ActivityServiceDetailsBinding binding;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;
    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;
    private static final int READ_PERMISSION = 101;

    ImageAdapter adapter;


    ArrayList<Uri> list = new ArrayList<Uri>();
    String latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Register Yourself");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#303030"));
        actionBar.setBackgroundDrawable(colorDrawable);
        binding = ActivityServiceDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new ImageAdapter(list);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerView.getContext(), DividerItemDecoration.HORIZONTAL);
        binding.recyclerView.setAdapter(adapter);

        //getting users location...

        ActivityCompat.requestPermissions( this,
                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    OnGPS();
                } else {
                    getLocation();
                }


                if (ContextCompat.checkSelfPermission(ServiceDetailsActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions( this,
                            new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PERMISSION);
                }

                binding.btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
                            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true );
                        }
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture "), 1);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            if (data.getClipData()!=null){
                int x = data.getClipData().getItemCount();

                for (int i=0;i<x;i++){
                    list.add(data.getClipData().getItemAt(i).getUri());
                }
                adapter.notifyDataSetChanged();
                binding.imgCount.setText("Photos ("+list.size()+")");
            }
            else if(data.getData() != null){
                String imageUrl = data.getData().getPath();
                list.add(Uri.parse(imageUrl));
            }

        }
    }

    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(
                ServiceDetailsActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                ServiceDetailsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();
                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);
                binding.tvLocation.setText("Your Location: " +
                        " " + "Latitude: " + latitude +
                        " " + "Longitude: " + longitude);
            } else {
                Toast.makeText(this, "Unable to find location.", Toast.LENGTH_SHORT).show();
            }
        }


        user = FirebaseAuth.getInstance().getCurrentUser();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("");


        binding.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceDetailsActivity.this, MapsActivity.class));
            }
        });

/*
        binding.btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        // Handle location changes here
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        LocationModel locationModel = new LocationModel(latitude, longitude);
                        reference.push().child("Location").setValue(locationModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ServiceDetailsActivity.this, "Location Added!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {
                        // Handle status changes here
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        // Handle provider enabled here
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        // Handle provider disabled here
                    }
                };


                if (ActivityCompat.checkSelfPermission(ServiceDetailsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ServiceDetailsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    ActivityCompat.requestPermissions(ServiceDetailsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                locationManager.removeUpdates(locationListener);

            }
        });

 */




        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = user.getUid();
                String sName = binding.etName.getText().toString();
                String price = binding.etPrice.getText().toString();
                ServiceModel service = new ServiceModel(userId, sName, price);
                reference.child("Gym Service").push().setValue(service).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ServiceDetailsActivity.this, "Added Successfully!!", Toast.LENGTH_SHORT).show();
                            binding.etName.setText("");
                            binding.etPrice.setText("");
                        }
                    }
                });
            }
        });
    }
}