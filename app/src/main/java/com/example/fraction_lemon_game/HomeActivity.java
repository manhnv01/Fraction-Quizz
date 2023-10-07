package com.example.fraction_lemon_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llStart, llSummary, llSum, llSub, llMul, llDiv;
    RatingBar rtStart, rtSummary, rtSum, rtSub, rtMul, rtDiv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getView();
        SharedPreferences sharedPreferences = getSharedPreferences("calculate", Context.MODE_PRIVATE);
        int sum = sharedPreferences.getInt("rtSum", 0);
        int sub = sharedPreferences.getInt("rtSub", 0);
        int mul = sharedPreferences.getInt("rtMul", 0);
        int div = sharedPreferences.getInt("rtDiv", 0);
        int start = sharedPreferences.getInt("rtStart", 0);
        int summary = sharedPreferences.getInt("rtSummary", 0);

        rtSum.setRating(sum);
        rtSub.setRating(sub);
        rtMul.setRating(mul);
        rtDiv.setRating(div);
        rtStart.setRating(start);
        rtSummary.setRating(summary);
    }

    private void getView(){
        llStart = findViewById(R.id.llStart);
        llStart.setOnClickListener(this);
        llSummary = findViewById(R.id.llSummary);
        llSummary.setOnClickListener(this);
        llSum = findViewById(R.id.llSum);
        llSum.setOnClickListener(this);
        llSub = findViewById(R.id.llSub);
        llSub.setOnClickListener(this);
        llMul = findViewById(R.id.llMul);
        llMul.setOnClickListener(this);
        llDiv = findViewById(R.id.llDiv);
        llDiv.setOnClickListener(this);

        rtStart = findViewById(R.id.rtStart);
        rtSummary = findViewById(R.id.rtSummary);
        rtSum = findViewById(R.id.rtSum);
        rtSub = findViewById(R.id.rtSub);
        rtMul = findViewById(R.id.rtMul);
        rtDiv = findViewById(R.id.rtDiv);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HomeActivity.this,QuestionCalActivity.class);
        switch (view.getId()) {
            case R.id.llSum:
                intent.putExtra("cal",1);
                break;
            case R.id.llSub:
                intent.putExtra("cal",2);
                break;
            case R.id.llMul:
                intent.putExtra("cal",3);
                break;
            case R.id.llDiv:
                intent.putExtra("cal",4);
                break;
            case R.id.llStart:
                intent.putExtra("cal",5);
                break;
            case R.id.llSummary:
                intent.putExtra("cal",6);
                break;
        }
        startActivity(intent);
    }
}