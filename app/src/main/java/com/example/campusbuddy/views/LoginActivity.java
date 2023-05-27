package com.example.campusbuddy.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.campusbuddy.MainActivity;
import com.example.campusbuddy.R;
import com.example.campusbuddy.databinding.ActivityLoginBinding;
import com.example.campusbuddy.utils.Utils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = LoginActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(LoginActivity.this, R.color.black));
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        findViewById(R.id.txtSignUp).setOnClickListener(this::onClick);

        binding.btnLogin.setOnClickListener(this::onClick);
        binding.txtSignUp.setOnClickListener(this::onClick);

        }

    public void transitionToSignUp(){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnLogin:
                login();
                break;

            case R.id.txtSignUp:
                transitionToSignUp();
                finish();
                break;

        }

    }

    public void login(){
        String txt_email = binding.edtEmail.getText().toString();
        String txt_password = binding.edtPassword.getText().toString();

        Utils.hideKeyboard(LoginActivity.this);

        if (TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
            Toast.makeText(LoginActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
        } else {

            dialog = Utils.showLoader(LoginActivity.this);
            auth.signInWithEmailAndPassword(txt_email, txt_password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                if(dialog!=null){
                                    dialog.dismiss();
                                }
                                startActivity(intent);

                            } else {
                                if(dialog!=null){
                                    dialog.dismiss();
                                }
                                Toast.makeText(LoginActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
