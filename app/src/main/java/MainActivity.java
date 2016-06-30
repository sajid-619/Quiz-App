package com.example.android.quizapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    float correctAnswers = 0;
    float totalQuestions = 5;
    boolean qOneanswered = false;
    boolean qTwoAnswered = false;
    boolean qThreeAnswered = false;
    boolean qFourAnswered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void questionOneAnswered(View v) {
        if (v.getId() == R.id.q_one_a) {
            correctAnswers += 1;
        } else {
            if (qOneanswered) {
                correctAnswers -= 1;
            }
        }
        qOneanswered = true;
    }

    public void questionTwoAnswered(View v) {
        switch (v.getId()) {
            case R.id.q_two_a:
                correctAnswers += 0.33;
                break;
            case R.id.q_two_b:
                correctAnswers += 0.33;
                break;
            case R.id.q_two_d:
                correctAnswers += 0.34;
                break;
            default:
                if (qTwoAnswered) {
                    correctAnswers -= 1;
                }
                break;
        }
        qTwoAnswered = true;
    }

    public void questionThreeAnswered(View v) {
        if (v.getId() == R.id.q_three_d) {
            correctAnswers += 1;
        } else {
            if (qThreeAnswered) {
                correctAnswers -= 1;
            }
        }
        qThreeAnswered = true;
    }

    public void questionFourAnswered(View v) {
        if (v.getId() == R.id.q_four_b) {
            correctAnswers += 1;
        } else {
            if (qFourAnswered) {
                correctAnswers -= 1;
            }
        }
        qFourAnswered = true;
    }

    private void uncheckAllChildren(ViewGroup vg) {
        for (int i = 0; i < vg.getChildCount(); i++) {
            View v = vg.getChildAt(i);
            if (v instanceof CheckBox) {
                ((CheckBox) v).setChecked(false);
            }
        }
    }

    public void submitAnswersTapped(View v) {
        EditText qFive = (EditText) findViewById(R.id.q_five_answer);
        if (qFive.getText().toString().equalsIgnoreCase("2014")) {
            correctAnswers += 1;
        }
        Log.v("Five Answer: ", qFive.getText().toString());

        displayScore();

    }

    private void displayScore() {
        Log.v("Correct Answers", Float.toString(correctAnswers));
        float score = correctAnswers / totalQuestions * 100;
        score = Math.round(score);
        Log.v("Score: ", Float.toString(score * 100) + "%");
        String scoreMessage = "Score: " + score + "%";
        Toast.makeText(MainActivity.this, scoreMessage, Toast.LENGTH_LONG).show();
        resetQuiz();
    }

    private void resetQuiz() {
        RadioGroup one = (RadioGroup) findViewById(R.id.q_one_group);
        one.clearCheck();

        RadioGroup three = (RadioGroup) findViewById(R.id.q_three_group);
        three.clearCheck();

        RadioGroup four = (RadioGroup) findViewById(R.id.q_four_group);
        four.clearCheck();

        LinearLayout checkboxes = (LinearLayout) findViewById(R.id.checkbox_question);
        uncheckAllChildren(checkboxes);

        correctAnswers = 0;
        qOneanswered = false;
        qTwoAnswered = false;
        qThreeAnswered = false;
        qFourAnswered = false;
    }

}
