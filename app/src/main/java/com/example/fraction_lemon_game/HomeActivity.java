package com.example.fraction_lemon_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout llStart, llSummary, llSum, llSub, llMul, llDiv;
    RatingBar rtStart, rtSummary, rtSum, rtSub, rtMul, rtDiv;
    ImageView imvSum, imvSub, imvMul, imvDiv, imvSummary;
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

        imvSum = findViewById(R.id.imvSum);
        imvSub = findViewById(R.id.imvSub);
        imvMul = findViewById(R.id.imvMul);
        imvDiv = findViewById(R.id.imvDiv);
        imvSummary = findViewById(R.id.imvSummary);

        if (start < 3)
            imvSum.setAlpha(0.4F);
        else
            imvSum.setAlpha(1F);
        if (sum < 3)
            imvSub.setAlpha(0.4F);
        else
            imvSub.setAlpha(1F);
        if (sub < 3)
            imvMul.setAlpha(0.4F);
        else
            imvMul.setAlpha(1F);
        if (mul < 3)
            imvDiv.setAlpha(0.4F);
        else
            imvDiv.setAlpha(1F);
        if (div < 3)
            imvSummary.setAlpha(0.4F);
        else
            imvSummary.setAlpha(1F);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(HomeActivity.this,QuestionCalActivity.class);
        switch (view.getId()) {
            case R.id.llStart:
                intent.putExtra("cal",1);
                startActivity(intent);
                break;
            case R.id.llSum:
                if (start >= 3){
                    intent.putExtra("cal",2);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Bạn cần 3* KHỞI ĐỘNG!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llSub:
                if (sum >= 3){
                    intent.putExtra("cal",3);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Bạn cần 3* PHÉP CỘNG!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llMul:
                if (sub >= 3){
                    intent.putExtra("cal",4);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Bạn cần 3* PHÉP TRỪ!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llDiv:
                if (mul >= 3){
                    intent.putExtra("cal",5);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Bạn cần 3* PHÉP NHÂN!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.llSummary:
                if (div >= 3){
                    intent.putExtra("cal",6);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Bạn cần 3* PHÉP CHIA!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}