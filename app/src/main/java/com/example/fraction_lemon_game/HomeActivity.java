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
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llStart, llSummary, llSum, llSub, llMul, llDiv;
    RatingBar rtStart, rtSummary, rtSum, rtSub, rtMul, rtDiv;
    int sum, sub, mul, div, start, summary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getView();
        SharedPreferences sharedPreferences = getSharedPreferences("calculate", Context.MODE_PRIVATE);
        sum = sharedPreferences.getInt("rtSum", 0);
        sub = sharedPreferences.getInt("rtSub", 0);
        mul = sharedPreferences.getInt("rtMul", 0);
        div = sharedPreferences.getInt("rtDiv", 0);
        start = sharedPreferences.getInt("rtStart", 0);
        summary = sharedPreferences.getInt("rtSummary", 0);

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
                if (start >= 3){
                    Toast.makeText(this, "Bạn cần 3* Khởi Động!", Toast.LENGTH_SHORT).show();
                    intent.putExtra("cal",1);
                    startActivity(intent);
                }
                break;
            case R.id.llSub:
                if (sum >= 3){
                    Toast.makeText(this, "Bạn cần 3* Tính Tổng!", Toast.LENGTH_SHORT).show();
                    intent.putExtra("cal",2);
                    startActivity(intent);
                }
                break;
            case R.id.llMul:
                if (sub >= 3){
                    Toast.makeText(this, "Bạn cần 3* Tính Trừ!", Toast.LENGTH_SHORT).show();
                    intent.putExtra("cal",3);
                    startActivity(intent);
                }
                break;
            case R.id.llDiv:
                if (mul >= 3){
                    Toast.makeText(this, "Bạn cần 3* Tính Tích!", Toast.LENGTH_SHORT).show();
                    intent.putExtra("cal",4);
                    startActivity(intent);
                }
                break;
            case R.id.llStart:
                intent.putExtra("cal",5);
                startActivity(intent);
                break;
            case R.id.llSummary:
                if (div >= 3){
                    Toast.makeText(this, "Bạn cần 3* Tính Thương!", Toast.LENGTH_SHORT).show();
                    intent.putExtra("cal",6);
                    startActivity(intent);
                }
                break;
        }
    }
}