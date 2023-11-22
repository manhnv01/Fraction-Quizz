package com.example.fraction_lemon_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    RatingBar ratingBar;
    ImageView imvClose;
    Button btnNext, btnRetry;
    TextView txtNotif;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtNotif = findViewById(R.id.txtNotif);
        ratingBar = findViewById(R.id.ratingBar);
        imvClose = findViewById(R.id.imvClose);
        btnNext = findViewById(R.id.btnNext);
        btnRetry = findViewById(R.id.btnRetry);

        Intent intent = getIntent();
        int star = intent.getIntExtra("star", 0);
        int question = intent.getIntExtra("question", 1);
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

        if(question > 5 || star < 3){
            btnNext.setVisibility(View.GONE);
        }
        else{
            btnNext.setVisibility(View.VISIBLE);
        }

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, HomeActivity.class));
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, QuestionCalActivity.class);
                intent.putExtra("cal", question + 1);
                startActivity(intent);
                finish();
            }
        });

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, QuestionCalActivity.class);
                intent.putExtra("cal", question);
                startActivity(intent);
                finish();
            }
        });
    }
}