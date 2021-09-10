package com.quiz.entity;

import java.util.List;

public class Question {
    private int id;
    private int testId;
    private String description;
    private List<Answer> answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isCorrect(int pos) {
        return getAnswers().get(pos).getAnswer() == 1;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
