package com.quiz.entity;

import java.sql.Timestamp;

public class Result {
    private int id;
    private int score;
    private int userId;
    private int testId;
    private String testHeader;
    private String topicName;
    private Timestamp startDate;
    private Timestamp completeDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setTestHeader(String testHeader) {
        this.testHeader = testHeader;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setCompleteDate(Timestamp completeDate) {
        this.completeDate = completeDate;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int getUserId() {
        return userId;
    }

    public int getTestId() {
        return testId;
    }

    public String getTestHeader() {
        return testHeader;
    }

    public String getTopicName() {
        return topicName;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getCompleteDate() {
        return completeDate;
    }

    public static class Builder {
        private Result result = new Result();

        public Builder setId(int id) {
            result.setId(id);
            return this;
        }

        public Builder setScore(int score) {
            result.setScore(score);
            return this;
        }

        public Builder setUserId(int userId) {
            result.setUserId(userId);
            return this;
        }

        public Builder setTestId(int testId) {
            result.setTestId(testId);
            return this;
        }

        public Builder setTestHeader(String testHeader) {
            result.setTestHeader(testHeader);
            return this;
        }

        public Builder setTopicName(String topicName) {
            result.setTopicName(topicName);
            return this;
        }

        public Builder setStartDate(Timestamp startDate) {
            result.setStartDate(startDate);
            return this;
        }

        public Builder setCompleteDate(Timestamp completeDate) {
            result.setCompleteDate(completeDate);
            return this;
        }

        public Result build() {
            return result;
        }
    }
}