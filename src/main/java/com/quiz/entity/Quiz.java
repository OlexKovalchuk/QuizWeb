package com.quiz.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Quiz implements Serializable {
    private int id;
    private String description;
    private String difficult;
    private int duration;
    private int topicId;
    private Date createDate;
    private String topicName;
    transient private List<Question> questions;
    private String header;
    private int count;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDifficult() {
        return difficult;
    }

    public int getDuration() {
        return duration;
    }

    public int getTopicId() {
        return topicId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getTopicName() {
        return topicName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getHeader() {
        return header;
    }

    public int getCount() {
        return count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static class Builder {
        private Quiz quiz = new Quiz();

        public Builder setId(int id) {
            quiz.setId(id);
            return this;
        }

        public Builder setDescription(String description) {
            quiz.setDescription(description);
            return this;
        }

        public Builder setDifficult(String difficult) {
            quiz.setDifficult(difficult);
            return this;
        }

        public Builder setDuration(int duration) {
            quiz.setDuration(duration);
            return this;
        }

        public Builder setTopicId(int topicId) {
            quiz.setTopicId(topicId);
            return this;
        }

        public Builder setCreateDate(Date createDate) {
            quiz.setCreateDate(createDate);
            return this;
        }

        public Builder setTopicName(String topicName) {
            quiz.setTopicName(topicName);
            return this;
        }

        public Builder setQuestions(List<Question> questions) {
            quiz.setQuestions(questions);
            return this;
        }

        public Builder setHeader(String header) {
            quiz.setHeader(header);
            return this;
        }

        public Builder setCount(int count) {
            quiz.setCount(count);
            return this;
        }public Quiz build(){
            return quiz;
        }

    }
}