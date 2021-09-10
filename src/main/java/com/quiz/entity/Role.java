package com.quiz.entity;

public enum Role {
    STUDENT("student"), TEACHER("teacher");

    Role(String roleName) {
        this.roleName = roleName;
    }

    private final String roleName;

    public String getRoleName() {
        return roleName;
    }

    public static Role getRoleFromString(String role) {
        if (role.equals("teacher")) {
            return TEACHER;
        }
        return STUDENT;
    }
}
