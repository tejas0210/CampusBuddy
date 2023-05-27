package com.example.campusbuddy.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.window.SplashScreen;

import com.example.campusbuddy.R;

public class SplashScreenActivity extends AppCompatActivity {

    TextView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Window window = SplashScreenActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(SplashScreenActivity.this, R.color.splashScreen));
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logo = (TextView) findViewById(R.id.logo);
        ObjectAnimator scaleDownX1 = ObjectAnimator.ofFloat(logo, "scaleX", 27);
        ObjectAnimator scaleDownY1 = ObjectAnimator.ofFloat(logo, "scaleY", 27);
        scaleDownX1.setDuration(1500);
        scaleDownY1.setDuration(1500);

        AnimatorSet scaleUp1 = new AnimatorSet();
        scaleUp1.play(scaleDownY1).with(scaleDownX1);

        scaleUp1.start();

//        txtAnandita = findViewById(R.id.txtAnandita);
//        ObjectAnimator scaleDownX2 = ObjectAnimator.ofFloat(txtAnandita, "scaleX", 225);
//        ObjectAnimator scaleDownY2 = ObjectAnimator.ofFloat(txtAnandita, "scaleY", 225);
//        scaleDownX2.setDuration(1500);
//        scaleDownY2.setDuration(1500);
//
//        AnimatorSet scaleUp2 = new AnimatorSet();
//        scaleUp2.play(scaleDownY2).with(scaleDownX2);
//
//        scaleUp2.start();



        Thread t = new Thread()
        {
            public void run() {
                try {
                    sleep(1700);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
//                    Intent intent = new Intent(SplashScreenActivity.this,LoginActivity.class);

                    // Temporary intent
                    Intent intent = new Intent(SplashScreenActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        t.start();
    }
}