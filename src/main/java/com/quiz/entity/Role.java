package com.quiz.entity;

public enum Role {
    STUDENT("student",1), ADMIN("admin",2);

    Role(String roleName,int id) {
        this.roleName = roleName;
        this.id = id;
    }

    private final String roleName;

    public int getId() {
        return id;
    }

    private final int id;

    public String getRoleName() {
        return roleName;
    }

    public static Role getRoleByName(String role) {
        if (role.equals("admin")) {
            return ADMIN;
        }
        return STUDENT;
    }
    public static Role getRoleById(int id) {
        if (id==2) {
            return ADMIN;
        }
        return STUDENT;
    }
}