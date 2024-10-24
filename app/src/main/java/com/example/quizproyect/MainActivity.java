package com.example.quizproyect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true)
    };
    private int points = 0;
    private HashMap<Integer, Boolean> answeredQuestions = new HashMap<>();
    private int mCurrentIndex = 0;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mQuestionTextView;
    private TextView mPointsDisplay;

    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestionTextId();
        mQuestionTextView.setText(question);
        if (answeredQuestions.get(question) != null){
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }
        else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        }
    }
    private void checkAnswer(boolean attempt) {
        boolean correctAnswer = mQuestionBank[mCurrentIndex].isAnswerTrue();
        if (attempt == correctAnswer){
            int message = (R.string.correct_toast);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            points++;
            mPointsDisplay.setText("Points: " + String.valueOf(points));
            answeredQuestions.put(mQuestionBank[mCurrentIndex].getQuestionTextId(), true);
        } else {
            int message = (R.string.incorrect_toast);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            points--;
            mPointsDisplay.setText("Points: " + String.valueOf(points));
            answeredQuestions.put(mQuestionBank[mCurrentIndex].getQuestionTextId(), false);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        mQuestionTextView = (TextView) findViewById(R.id.questionDisplay);
        mPreviousButton = (Button) findViewById(R.id.previousButton);
        mNextButton = (Button) findViewById(R.id.nextButton);
        mTrueButton = (Button) findViewById(R.id.trueButton);
        mFalseButton = (Button) findViewById(R.id.falseButton);
        mPointsDisplay = (TextView) findViewById(R.id.pointsDisplay);
        mPointsDisplay.setText("Points: " + String.valueOf(points));

        updateQuestion();
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);
                checkAnswer(true);
            }
        });


        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTrueButton.setEnabled(false);
                mFalseButton.setEnabled(false);
                checkAnswer(false);
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                if (mCurrentIndex < 0){
                    mCurrentIndex = mQuestionBank.length - 1;
                }

                updateQuestion();
            }
        });


    }
}