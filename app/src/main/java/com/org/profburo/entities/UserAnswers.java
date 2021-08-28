package com.org.profburo.entities;

import java.util.HashMap;

public class UserAnswers {
    private Integer userId;
    private Integer quizsetId;
    private HashMap<Integer, Integer> answers;

    public UserAnswers() {
        answers = new HashMap<>();
    }

    public void addAnswer(int questionId, int answerId)
    {
        answers.put(questionId, answerId);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQuizsetId() {
        return quizsetId;
    }

    public void setQuizsetId(Integer quizsetId) {
        this.quizsetId = quizsetId;
    }

    public HashMap<Integer, Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(HashMap<Integer, Integer> answers) {
        this.answers = answers;
    }
}
