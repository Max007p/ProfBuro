package com.org.profburo.network.responsesEntities.test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AnswerQuestionBind {
    @SerializedName("question")
    @Expose
    private Question question;
    @SerializedName("answers")
    @Expose
    private List<Answer> answers = null;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
