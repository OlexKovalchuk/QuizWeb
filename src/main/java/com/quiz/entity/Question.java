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

    public int getCorrectAnswerCount() {
        int count = 0;
        for (Answer answer : answers) {
            if (answer.getAnswer() == 1) count++;
        }
        return count;
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

    public boolean isCorrect(List<Integer>answersList) {
        for (int i = 0; i < answers.size(); i++) {
            if ((answers.get(i).getAnswer() == 1 && !answersList.contains(i)) || (answers.get(i).getAnswer() == 0 && answersList.contains(i))) {
                return false;
            }
        }
        return true;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}