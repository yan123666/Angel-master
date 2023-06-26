package com.example.advertisemen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.angel.R;
import com.example.login.LoginActivity;

public class Advertisement extends AppCompatActivity {
    private Button button;
    private Handler handler = new Handler();
    TimeCount timeCount;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            tomainActivity();
        }
    };

    private void tomainActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_advertisement);

        initViews();
        handler.postDelayed(runnable,3000);

        timeCount = new TimeCount(4000,1000);
        timeCount.start();
    }

    private void initViews() {
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomainActivity();
            }
        });
    }

    class TimeCount extends CountDownTimer{

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long l) {
            button.setText(l/1000+ "秒");
        }

        @Override
        public void onFinish() {
            handler.removeCallbacks(runnable);
        }
    }

}