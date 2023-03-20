package com.example.campusbuddy.views;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.campusbuddy.R;
import com.example.campusbuddy.adapters.ImageAdapter;
import com.example.campusbuddy.databinding.ActivityServiceDetailsBinding;
import com.example.campusbuddy.model.ServiceModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ServiceDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int PICK_IMAGE_REQUEST_CODE = 200;
    ActivityServiceDetailsBinding binding;
    FirebaseUser user;
    FirebaseDatabase database;
    private Bitmap bitmap;
    DatabaseReference reference;

    Uri selectedImageUri;
    private FirebaseStorage storage;
    String serviceType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityServiceDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window window = ServiceDetailsActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(ServiceDetailsActivity.this, R.color.arsenic));
        // assigning ID of the toolbar to a variable
        Toolbar toolbar = findViewById(R.id.toolbar);
        // using toolbar as ActionBar
        setSupportActionBar(toolbar);
        setTitle("Register Yourself");


        user = FirebaseAuth.getInstance().getCurrentUser();
        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();



        // DROPDOWN SPINNER CODE BELOW....
        Spinner spinner = findViewById(R.id.my_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.my_spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                serviceType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //FINISH



        String loc = getIntent().getStringExtra("LocationToSet");
        binding.edtLocation.setText(loc);

        binding.coverImage.setOnClickListener(this);

    }

    // Image Selection Code
    void imageChooser() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 2000);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == 2000) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    binding.coverImage.setImageURI(selectedImageUri);
                }
            }
        }
    }




    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.coverImage:
                imageChooser();
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
            case R.id.btn_Submit:
                btnSubmit();
        }
    }




    //data submission code
    private void btnSubmit(){
        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = user.getUid();
                String sName = binding.etName.getText().toString();
                String price = binding.etPrice.getText().toString();
                String location = binding.edtLocation.getText().toString();
                ServiceModel service = new ServiceModel(userId,serviceType ,sName, price, selectedImageUri.toString(),location);
                reference.child(serviceType).push().setValue(service).addOnCompleteListener(new OnCompleteListener<Void>() {
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