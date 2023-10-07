package com.example.fraction_lemon_game;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

public class QuestionCalActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtTS1, txtMS1, txtTS2, txtMS2, txtTS3, txtMS3, txtTS4, txtMS4, txtCal, txtEnd, txtQuestion, txtAnswerA, txtAnswerB, txtAnswerC, txtAnswerD;
    LinearLayout llAnswerA, llAnswerB, llAnswerC, llAnswerD, llAnswer, llImage, llPSA, llPSB, llPSC, llPSD;
    Button btnCheck, btnContinue;
    RadioGroup radioGroup;
    RadioButton rb1,rb2,rb3,rb4;
    ImageView imv1, imv2, imvClose, imvButton;
    private HorizontalStepView mSetpview0;
    String[] images1, images2, answerTS, answerMS, optTS, optMS;
    String[] questions = {
            "Khái niệm phân số là gì?",
            "Điền dấu thích hợp vào '?' để so sánh hai phân số sau:",
            "Rút gọn phân số sau:",
            "Nêu quy tắc nhân hai phân số.",
            "Chọn đáp án đúng cho quy tắc chia hai phân số."
    };
    String[] opt = {
            "Phân số là sự biểu diễn của hai số hữu tỉ dưới dạng tỉ lệ của hai số nguyên, trong đó số nguyên ở trên được gọi là tử số, còn số nguyên ở dưới được gọi là mẫu số. Điều kiện bắt buộc là mẫu số phải khác số 0.",
            "Phân số là sự biểu diễn của hai số hữu tỉ dưới dạng tỉ lệ của hai số nguyên",
            "Phân số là sự biểu diễn của hai số hữu tỉ .Điều kiện bắt buộc là mẫu số phải khác số 0.",
            "Phân số là phải có tử và mẫu",
            ">","<","=","<=",
            "import","this","catch","abstract",
            "Muốn nhân hai phân số ta lấy tử số nhân với tử số, mẫu số nhân với mẫu số.",
            "Muốn nhân hai phân số, ta lấy phân số thứ nhất nhân với phân số thứ hai đảo ngược.",
            "Muốn nhân hai phân số ta lấy tử số nhân với mẫu số",
            "Tất cả đều đúng",
            "Muốn chia hai phân số ta lấy tử số chia tử số, mẫu số chia với mẫu số.",
            "Muốn chia hai phân số, ta lấy phân số thứ nhất nhân với phân số thứ hai đảo ngược.",
            "Muốn chia hai phân số, ta lấy phân số thứ hai nhân với phân số thứ nhất đảo ngược.",
            "Đáp án khác"
    };
    String[] cals = {"+", "x", "x", ":", "-"};
    SharedPreferences sharedPreferences;
    int flag = 0, cal;
    int number = 0;
    int marks = 0, correct = 0, wrong = 0;

    List<StepBean> stepsBeanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_cal);

        getView(); // ánh xạ
        SharedPreferences.Editor editor = sharedPreferences.edit();
        final MediaPlayer correct_sound = MediaPlayer.create(QuestionCalActivity.this, R.raw.correct);
        final MediaPlayer wrong_sound = MediaPlayer.create(QuestionCalActivity.this, R.raw.wrong);

        // Khởi tạp Step Bar
        stepsBeanList = new ArrayList<>();
        StepBean stepBean0 = new StepBean("",-1);
        StepBean stepBean1 = new StepBean("",-1);
        StepBean stepBean2 = new StepBean("",-1);
        StepBean stepBean3 = new StepBean("",-1);
        StepBean stepBean4 = new StepBean("",-1);
        stepsBeanList.add(stepBean0);
        stepsBeanList.add(stepBean1);
        stepsBeanList.add(stepBean2);
        stepsBeanList.add(stepBean3);
        stepsBeanList.add(stepBean4);

        mSetpview0.setStepViewTexts(stepsBeanList)
                .setTextSize(12)//set textSize
                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(QuestionCalActivity.this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(QuestionCalActivity.this, R.color.white))//设置StepsViewIndicator未完成线的颜色
                .setStepViewComplectedTextColor(ContextCompat.getColor(QuestionCalActivity.this, android.R.color.white))//设置StepsView text完成线的颜色
                .setStepViewUnComplectedTextColor(ContextCompat.getColor(QuestionCalActivity.this, R.color.white))//设置StepsView text未完成线的颜色
                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(QuestionCalActivity.this, R.drawable.accept))//设置StepsViewIndicator CompleteIcon
                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(QuestionCalActivity.this, R.drawable.next))//设置StepsViewIndicator DefaultIcon
                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(QuestionCalActivity.this, R.drawable.multiply));//设置StepsViewIndicator AttentionIcon



        btnCheck.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "ResourceAsColor"})
            @Override
            public void onClick(View v) {
                if(number == 0)
                {
                    Toast.makeText(getApplicationContext(), "Hãy chọn một câu trả lời!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String ansTextTS;
                String ansTextMS;

                switch (number) {
                    case 1:
                        ansTextTS = txtTS1.getText().toString();
                        ansTextMS = txtMS1.getText().toString();
                        llAnswer = findViewById(R.id.llA);
                        break;
                    case 2:
                        ansTextTS = txtTS2.getText().toString();
                        ansTextMS = txtMS2.getText().toString();
                        llAnswer = findViewById(R.id.llB);
                        break;
                    case 3:
                        ansTextTS = txtTS3.getText().toString();
                        ansTextMS = txtMS3.getText().toString();
                        llAnswer = findViewById(R.id.llC);
                        break;
                    default:
                        ansTextTS = txtTS4.getText().toString();
                        ansTextMS = txtMS4.getText().toString();
                        llAnswer = findViewById(R.id.llD);
                        break;
                }
                btnCheck.setVisibility(View.GONE);
                disable();

                if(ansTextTS.equals(answerTS[flag]) && ansTextMS.equals(answerMS[flag])) {
                    correct++;
                    correct_sound.start();
                    btnContinue.setVisibility(View.VISIBLE);
                    btnContinue.setText("Tiếp tục");
                    imvButton.setImageResource(R.drawable.lemon_1);
                    btnContinue.setBackgroundColor(ContextCompat.getColor(QuestionCalActivity.this, R.color.button_green));
                    llAnswer.setBackgroundResource(R.drawable.back_answer_correct);

                    StepBean newStepBean1 = new StepBean("", 1);
                    stepsBeanList.set(flag, newStepBean1);
                }
                else {
                    wrong++;
                    wrong_sound.start();
                    btnContinue.setVisibility(View.VISIBLE);
                    btnContinue.setText("Đã hiểu");
                    imvButton.setImageResource(R.drawable.lemon_0);
                    btnContinue.setBackgroundColor(ContextCompat.getColor(QuestionCalActivity.this, R.color.button_red));
                    llAnswer.setBackgroundResource(R.drawable.back_answer_wrong);
                    StepBean newStepBean1 = new StepBean("", 0);
                    stepsBeanList.set(flag, newStepBean1);
                }
                mSetpview0.setStepViewTexts(stepsBeanList)
                        .setTextSize(12)//set textSize
                        .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(QuestionCalActivity.this, android.R.color.white))//设置StepsViewIndicator完成线的颜色
                        .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(QuestionCalActivity.this, R.color.white))//设置StepsViewIndicator未完成线的颜色
                        .setStepViewComplectedTextColor(ContextCompat.getColor(QuestionCalActivity.this, android.R.color.white))//设置StepsView text完成线的颜色
                        .setStepViewUnComplectedTextColor(ContextCompat.getColor(QuestionCalActivity.this, R.color.white))//设置StepsView text未完成线的颜色
                        .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(QuestionCalActivity.this, R.drawable.accept))//设置StepsViewIndicator CompleteIcon
                        .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(QuestionCalActivity.this, R.drawable.next))//设置StepsViewIndicator DefaultIcon
                        .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(QuestionCalActivity.this, R.drawable.multiply));//设置StepsViewIndicator AttentionIcon

                flag++;
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                number = 0;
                btnCheck.setVisibility(View.VISIBLE);
                btnContinue.setVisibility(View.GONE);
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(false);

                llAnswerA.setBackgroundResource(R.drawable.back_answer);
                llAnswerB.setBackgroundResource(R.drawable.back_answer);
                llAnswerC.setBackgroundResource(R.drawable.back_answer);
                llAnswerD.setBackgroundResource(R.drawable.back_answer);
                imvButton.setImageResource(R.drawable.lemon);
                enable();
                if(flag < 5)
                {
                    if(cal == 6 && flag == 1){
                        llImage.setVisibility(View.GONE);

                        setVisibleAnswerTxt();
                        setGoneAnswerPs();

                        txtTS2.setText(optTS[flag*4 +1]);
                        txtMS2.setText(optMS[flag*4 +1]);

                        txtQuestion.setText("Chọn đáp án đúng cho quy tắc chia hai phân số?");
                        txtAnswerA.setText("Muốn chia hai phân số ta lấy tử số nhân chia tử số, mẫu số chia với mẫu số.");
                        txtAnswerB.setText("Muốn chia hai phân số, ta lấy phân số thứ nhất nhân với phân số thứ hai đảo ngược.");
                        txtAnswerC.setText("Muốn chia hai phân số,ta lấy phân số thứ hai nhân với phân số thứ nhất đảo ngược.");
                        txtAnswerD.setText("Đáp án khác");
                    } else if (cal == 5 && flag == 0 || (cal == 5 && flag == 3) || (cal == 5 && flag == 4)) {
                        llImage.setVisibility(View.GONE);
                        setVisibleAnswerTxt();
                        setGoneAnswerPs();
                        setTextQuestion();
                        setTextQuestionTheory();
                    } else if (cal == 5 && flag == 1) {
                        llImage.setVisibility(View.VISIBLE);
                        txtCal.setVisibility(View.VISIBLE);
                        setVisibleAnswerTxt();
                        setGoneAnswerPs();
                        setTextQuestion();
                        setTextQuestionTheory();
                    } else if (cal == 5 && flag == 2) {
                        txtQuestion.setText(questions[flag]);
                        setGoneAnswerTxt();
                        setVisibleAnswerPs();
                        setTextQuestion();
                        llImage.setVisibility(View.VISIBLE);
                        txtCal.setVisibility(View.GONE);
                        imv2.setVisibility(View.GONE);
                    }else {
                        txtQuestion.setText("Chọn đáp án đúng cho phép tính dưới đây:");
                        llImage.setVisibility(View.VISIBLE);
                        setGoneAnswerTxt();
                        setVisibleAnswerPs();
                        if(cal == 6)
                            txtCal.setText(cals[flag]);
                        setTextQuestion();
                    }
                }
                else
                {
                    marks = correct;
                    switch (cal) {
                        case 1: // Phép cộng
                            editor.putInt("rtSum", marks);
                            break;
                        case 2: // Phép trừ
                            editor.putInt("rtSub", marks);
                            break;
                        case 3: // Phép nhân
                            editor.putInt("rtMul", marks);
                            break;
                        case 4: // Phép chia
                            editor.putInt("rtDiv", marks);
                            break;
                        case 5: // Khởi động
                            editor.putInt("rtStart", marks);
                            break;
                        default: // Tổng kết
                            editor.putInt("rtSummary", marks);
                            break;
                    }
                    editor.apply();

                    Intent in = new Intent(QuestionCalActivity.this, ResultActivity.class);
                    in.putExtra("star", marks);
                    startActivity(in);
                    finish();
                }
            }
        });

        imvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @SuppressLint("SetTextI18n")
    private void getView() {
        Intent intent = getIntent();
        cal = intent.getIntExtra("cal", 1);
        sharedPreferences = getSharedPreferences("calculate", MODE_PRIVATE);
        mSetpview0 = findViewById(R.id.step_view2);

        imv1 = findViewById(R.id.imv1);
        imv2 = findViewById(R.id.imv2);
        imvClose = findViewById(R.id.imvClose);
        imvButton = findViewById(R.id.imvButton);

        txtQuestion = findViewById(R.id.txtQuestion);
        txtEnd = findViewById(R.id.txtEnd);

        txtTS1 = findViewById(R.id.txtTs1);
        txtMS1 = findViewById(R.id.txtMs1);
        txtTS2 = findViewById(R.id.txtTs2);
        txtMS2 = findViewById(R.id.txtMs2);
        txtTS3 = findViewById(R.id.txtTs3);
        txtMS3 = findViewById(R.id.txtMs3);
        txtTS4 = findViewById(R.id.txtTs4);
        txtMS4 = findViewById(R.id.txtMs4);
        txtCal = findViewById(R.id.txtCal);

        txtAnswerA = findViewById(R.id.txtAnswerA);
        txtAnswerB = findViewById(R.id.txtAnswerB);
        txtAnswerC = findViewById(R.id.txtAnswerC);
        txtAnswerD = findViewById(R.id.txtAnswerD);

        btnCheck = findViewById(R.id.btnCheck);
        btnContinue = findViewById(R.id.btnContinue);

        radioGroup = findViewById(R.id.radioGroup);

        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);

        llPSA = findViewById(R.id.llPSA);
        llPSB = findViewById(R.id.llPSB);
        llPSC = findViewById(R.id.llPSC);
        llPSD = findViewById(R.id.llPSD);
        llImage = findViewById(R.id.llImage);
        llAnswerA = findViewById(R.id.llA);
        llAnswerA.setOnClickListener(this);
        llAnswerB = findViewById(R.id.llB);
        llAnswerB.setOnClickListener(this);
        llAnswerC = findViewById(R.id.llC);
        llAnswerC.setOnClickListener(this);
        llAnswerD = findViewById(R.id.llD);
        llAnswerD.setOnClickListener(this);

        switch (cal){
            case 1: // Phép cộng
                images1 = new String[] {"a1_phan_8","a2_phan_8","a4_phan_8","a7_phan_8","a2_phan_8"};
                images2 = new String[] {"a3_phan_8","a7_phan_8","a2_phan_8","a4_phan_8","a5_phan_8"};
                answerTS = new String[] {"1","9","3","11","7"};
                answerMS = new String[] {"2","8","4","8","8"};
                optTS = new String[] {
                        "1","7","6","5",
                        "9","3","5","7",
                        "6","3","10","3",
                        "5","11","1","9",
                        "5","6","7","9"
                };
                optMS = new String[] {
                        "2","8","8","8",
                        "8","8","8","8",
                        "4","8","8","4",
                        "4","8","2","8",
                        "4","8","8","8"
                };
                txtCal.setText("+");
                break;
            case 2: // Phép trừ
                images1 = new String[] {"a8_phan_8","a3_phan_8","a6_phan_8","a7_phan_8","a4_phan_8"};
                images2 = new String[] {"a1_phan_8","a2_phan_8","a3_phan_8","a2_phan_8","a6_phan_8"};
                answerTS = new String[] {"7","1","3","5","-1"};
                answerMS = new String[] {"8","8","8","8","4"};
                optTS = new String[] {
                        "1","7","6","5",
                        "1","3","5","7",
                        "5","3","10","3",
                        "5","11","3","9",
                        "1","11","3","-1"
                };
                optMS = new String[] {
                        "8","8","8","8",
                        "8","4","7","8",
                        "4","8","8","4",
                        "8","8","4","8",
                        "4","8","4","4"
                };
                txtCal.setText("-");
                break;
            case 3: // Phép nhân
                images1 = new String[] {"layer8","tao_2_tren_3","dua_6_tren_8","a2_phan_8","layer5"};
                images2 = new String[] {"dua_1_tren_8","chanh_2_tren_4","chanh_1_tren_4","chanh_3_tren_4","duahau_3_tren_6"};
                answerTS = new String[] {"1","1","3","3","1"};
                answerMS = new String[] {"8","3","16","16","2"};
                optTS = new String[] {
                        "1","7","6","5",
                        "1","1","5","7",
                        "5","3","3","3",
                        "3","11","3","9",
                        "1","1","3","7"
                };
                optMS = new String[] {
                        "8","8","8","8",
                        "2","3","7","8",
                        "4","16","18","4",
                        "16","8","4","8",
                        "4","2","4","8"
                };
                txtCal.setText("x");
                break;
            case 4: // Phép chia
                images1 = new String[] {"layer4","tao_2_tren_3","dua_6_tren_8","duahau_4_tren_6","tao_1_tren_3"};
                images2 = new String[] {"layer6","duahau_3_tren_6","tao_1_tren_3","chanh_3_tren_4","layer5"};
                answerTS = new String[] {"1","4","9","8","1"};
                answerMS = new String[] {"2","3","4","9","3"};
                optTS = new String[] {
                        "1","6","1","5",
                        "4","5","8","7",
                        "5","15","3","9",
                        "3","11","3","8",
                        "1","1","3","7"
                };
                optMS = new String[] {
                        "4","8","2","8",
                        "3","6","3","8",
                        "4","16","18","4",
                        "16","8","4","9",
                        "7","3","4","8"
                };
                txtCal.setText(":");
                break;
            case 5: // Khởi động
                images1 = new String[] {"tao_2_tren_3","duahau_5_tren_6","dua_5_tren_8_1","duahau_4_tren_6","tao_1_tren_3"};
                images2 = new String[] {"layer6","duahau_4_tren_6","tao_1_tren_3","chanh_3_tren_4","layer5"};
                answerTS = new String[] {"1","2","1","4","5"};
                answerMS = new String[] {"1","2","2","4","5"};
                optTS = new String[] {
                        "1","9","9","9",
                        "2","9","9","9",
                        "2","1","3","4",
                        "4","9","9","9",
                        "9","5","9","9"
                };
                optMS = new String[] {
                        "1","9","9","9",
                        "2","9","9","9",
                        "3","2","2","3",
                        "4","9","9","9",
                        "9","5","9","9"
                };
                txtCal.setText("?");
                txtEnd.setVisibility(View.GONE);
                break;
            default: // Tổng kết
                images1 = new String[] {"a7_phan_8", "tao_1_tren_3", "layer5","tao_1_tren_3","a6_phan_8"};
                images2 = new String[] {"a3_phan_8","duahau_3_tren_6","duahau_3_tren_6","layer6","a3_phan_8"};
                answerTS = new String[] {"5","4","1","1","3"};
                answerMS = new String[] {"4","3","2","3","8"};
                optTS = new String[] {
                        "5","11","1","9",
                        "2","4","8","7",
                        "1","1","3","7",
                        "1","6","1","5",
                        "5","3","10","3"
                };
                optMS = new String[] {
                        "4","8","2","8",
                        "8","3","1","8",
                        "4","2","4","8",
                        "4","8","3","8",
                        "4","8","8","4"
                };
                txtCal.setText("+");
                break;
        }

        setTextQuestion();

        if(cal == 5){
            llImage.setVisibility(View.GONE);
            setTextQuestionTheory();
            setVisibleAnswerTxt();
            setGoneAnswerPs();
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.llA:
            case R.id.radioButton:
                number = 1;
                rb1.setChecked(true);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(false);
                break;
            case R.id.llB:
            case R.id.radioButton2:
                number = 2;
                rb1.setChecked(false);
                rb2.setChecked(true);
                rb3.setChecked(false);
                rb4.setChecked(false);
                break;
            case R.id.llC:
            case R.id.radioButton3:
                number = 3;
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(true);
                rb4.setChecked(false);
                break;
            case R.id.llD:
            case R.id.radioButton4:
                number = 4;
                rb1.setChecked(false);
                rb2.setChecked(false);
                rb3.setChecked(false);
                rb4.setChecked(true);
                break;
        }
    }

    private void disable(){
        llAnswerA.setClickable(false);
        llAnswerB.setClickable(false);
        llAnswerC.setClickable(false);
        llAnswerD.setClickable(false);
    }
    private void enable(){
        llAnswerA.setClickable(true);
        llAnswerB.setClickable(true);
        llAnswerC.setClickable(true);
        llAnswerD.setClickable(true);
    }

    private void setTextQuestion(){
        txtTS1.setText(optTS[flag*4]);
        txtMS1.setText(optMS[flag*4]);

        txtTS2.setText(optTS[flag*4 +1]);
        txtMS2.setText(optMS[flag*4 +1]);

        txtTS3.setText(optTS[flag*4 +2]);
        txtMS3.setText(optMS[flag*4 +2]);

        txtTS4.setText(optTS[flag*4 +3]);
        txtMS4.setText(optMS[flag*4 +3]);

        // Tên của hình ảnh từ mảng (ví dụ: image1.jpg)
        String imageName1 = images1[flag];
        String imageName2 = images2[flag];

        // Lấy ID tài nguyên hình ảnh từ tên
        @SuppressLint("DiscouragedApi")
        int imageResourceId1 = getResources().getIdentifier(imageName1, "drawable", getPackageName());
        @SuppressLint("DiscouragedApi")
        int imageResourceId2 = getResources().getIdentifier(imageName2, "drawable", getPackageName());

        // Sử dụng ID tài nguyên để hiển thị hình ảnh trong ImageView
        imv1.setImageResource(imageResourceId1);
        imv2.setImageResource(imageResourceId2);
    }
    private void setTextQuestionTheory(){
        txtQuestion.setText(questions[flag]);
        txtAnswerA.setText(opt[flag*4]);
        txtAnswerB.setText(opt[flag*4 +1]);
        txtAnswerC.setText(opt[flag*4 +2]);
        txtAnswerD.setText(opt[flag*4 +3]);
    }
    private void setVisibleAnswerTxt(){
        txtAnswerA.setVisibility(View.VISIBLE);
        txtAnswerB.setVisibility(View.VISIBLE);
        txtAnswerC.setVisibility(View.VISIBLE);
        txtAnswerD.setVisibility(View.VISIBLE);
    }
    private void setGoneAnswerTxt(){
        txtAnswerA.setVisibility(View.GONE);
        txtAnswerB.setVisibility(View.GONE);
        txtAnswerC.setVisibility(View.GONE);
        txtAnswerD.setVisibility(View.GONE);
    }
    private void setVisibleAnswerPs(){
        llPSA.setVisibility(View.VISIBLE);
        llPSB.setVisibility(View.VISIBLE);
        llPSC.setVisibility(View.VISIBLE);
        llPSD.setVisibility(View.VISIBLE);
    }
    private void setGoneAnswerPs(){
        llPSA.setVisibility(View.GONE);
        llPSB.setVisibility(View.GONE);
        llPSC.setVisibility(View.GONE);
        llPSD.setVisibility(View.GONE);
    }
}
