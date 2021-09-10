package com.quiz.entity;

import java.sql.Timestamp;

public class Result {
    private int id;
    private int score;
    private int userId;
    private int testId;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    private String testHeader;
    private String topicName;
    private Timestamp startDate;
    private Timestamp completeDate;

    public String getTestHeader() {
        return testHeader;
    }

    public void setTestHeader(String testHeader) {
        this.testHeader = testHeader;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(Timestamp completeDate) {
        this.completeDate = completeDate;
    }
}
