package com.quiz.entity;

public enum Role {
    STUDENT("student"), TEACHER("admin");

    Role(String roleName) {
        this.roleName = roleName;
    }

    private final String roleName;

    public String getRoleName() {
        return roleName;
    }

    public static Role getRoleFromString(String role) {
        if (role.equals("admin")) {
            return TEACHER;
        }
        return STUDENT;
    }
}
