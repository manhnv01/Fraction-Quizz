package com.example.fraction_lemon_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekbar);
        handler = new Handler(Looper.getMainLooper());

        // Gọi hàm startTask() để bắt đầu công việc định kỳ
        startTask();
    }

    private void startTask() {
        final int delayMillis = 20; // Độ trễ giữa các lần chạy công việc (20 mili giây trong trường hợp này)

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (seekBar.getProgress() == 100) {
                    Log.i("load", String.valueOf(seekBar.getProgress()));
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    finish();
                } else {
                    seekBar.setProgress(seekBar.getProgress() + 1);
                    // Gọi lại hàm startTask() để thực hiện công việc tiếp theo sau độ trễ
                    startTask();
                }
            }
        }, delayMillis);
    }

}