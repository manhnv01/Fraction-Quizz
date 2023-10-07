package com.example.fraction_lemon_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    RatingBar ratingBar;
    Button btnGoHome;
    TextView txtNotif;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtNotif = findViewById(R.id.txtNotif);
        ratingBar = findViewById(R.id.ratingBar);
        btnGoHome = findViewById(R.id.btnGoHome);

        Intent intent = getIntent();
        int star = intent.getIntExtra("star", 0);
        ratingBar.setRating(star);
        final MediaPlayer cheer_sound = MediaPlayer.create(ResultActivity.this, R.raw.cheer);
        final MediaPlayer try_again_sound = MediaPlayer.create(ResultActivity.this, R.raw.tryagain);
        if(star < 3) {
            txtNotif.setText("Cố gắng lần sau!");
            try_again_sound.start();
        }
        else {
            cheer_sound.start();
        }

        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, HomeActivity.class));
                finish();
            }
        });
    }
}