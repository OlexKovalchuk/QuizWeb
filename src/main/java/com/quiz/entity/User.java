package com.quiz.entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    private int block;
    private double averageScore;
    private Date creationDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public int getBlock() {
        return block;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public static class Builder{
        private User user = new User();
        public Builder setId(int id) {
            user.setId(id);
            return this;
        }

        public Builder setName(String name) {
            user.setName(name);
            return this;
        }

        public Builder setSurname(String surname) {
            user.setSurname(surname);
            return this;
        }

        public Builder setEmail(String email) {
            user.setEmail(email);
            return this;
        }
        public Builder setPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder setRole(Role role) {
            user.setRole(role);
            return this;
        }

        public Builder setBlock(int block) {
            user.setBlock(block);
            return this;
        }

        public Builder setAverageScore(double averageScore) {
            user.setAverageScore(averageScore);
            return this;
        }

        public Builder setCreationDate(Date creationDate) {
            user.setCreationDate(creationDate);
            return this;
        }

        public User build() {
            return user;
        }
    }
}