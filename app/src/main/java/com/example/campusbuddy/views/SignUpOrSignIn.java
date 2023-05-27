package com.example.campusbuddy.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.campusbuddy.R;
import com.example.campusbuddy.databinding.ActivityHomeBinding;
import com.example.campusbuddy.databinding.ActivitySignUpOrSignInBinding;

public class SignUpOrSignIn extends AppCompatActivity implements View.OnClickListener {

    ActivitySignUpOrSignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpOrSignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //For status bar color
        Window window = SignUpOrSignIn.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(SignUpOrSignIn.this, R.color.black));

        binding.btnSignIn2.setOnClickListener(this);
        binding.btnSignUp2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn2:
                transitionToSignIn();
                break;
            case R.id.btnSignUp2:
                transitionToSignUp();
                break;
        }
    }

    public void transitionToSignIn(){
        Intent intent = new Intent(SignUpOrSignIn.this,LoginActivity.class);
        startActivity(intent);
    }

    public void transitionToSignUp(){
        Intent intent = new Intent(SignUpOrSignIn.this,RegisterActivity.class);
        startActivity(intent);
    }
}