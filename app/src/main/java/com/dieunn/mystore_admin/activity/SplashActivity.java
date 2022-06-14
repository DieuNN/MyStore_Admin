package com.dieunn.mystore_admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.dieunn.mystore_admin.MainActivity;
import com.dieunn.mystore_admin.R;
import com.google.firebase.FirebaseApp;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        FirebaseApp.initializeApp(getApplicationContext());
        ProgressBar progressBar=findViewById(R.id.progress_bar);
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, DangNhapActivity.class));
                finish();
            }
        },1000);
    }
}