package com.example.quizproyect;

public class Question {
    private int mQuestionTextId;
    private boolean mAnswerTrue;

    public int getQuestionTextId() {
        return mQuestionTextId;
    }

    public void setQuestionTextId(int questionTextId) {
        mQuestionTextId = questionTextId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(int questionTextId, boolean answerTrue) {
        mQuestionTextId = questionTextId;
        mAnswerTrue = answerTrue;
    }

}
