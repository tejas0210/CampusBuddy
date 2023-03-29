package com.example.campusbuddy.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;

import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.example.campusbuddy.R;
import com.example.campusbuddy.databinding.ActivityServiceDetailsBinding;
import com.example.campusbuddy.model.ServiceModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ServiceDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST_CODE = 200;
    ActivityServiceDetailsBinding binding;
    FirebaseUser user;
    FirebaseDatabase database;
    StorageReference storageReference;
    DatabaseReference reference;


    String sName,price,location;
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
        sName = binding.etName.getText().toString();
        price = binding.etPrice.getText().toString();
        location = binding.edtLocation.getText().toString();


        user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


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



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.coverImage:
//                imageChooser();
                Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
            case R.id.btn_Submit:

                if (TextUtils.isEmpty(sName) || TextUtils.isEmpty(price) || TextUtils.isEmpty(location)){
                    Toast.makeText(ServiceDetailsActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else if (serviceType == "Choose Service" ){
                    Toast.makeText(ServiceDetailsActivity.this, "Please select valid service type", Toast.LENGTH_SHORT).show();
                } else {
                    btnSubmit();
                }
                break;

        }
    }





    //data submission code
    private void btnSubmit(){

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = user.getUid();


                ServiceModel service = new ServiceModel(userId,serviceType ,sName, price, location);
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

    public String getServiceType(){
        return serviceType;
    }
}