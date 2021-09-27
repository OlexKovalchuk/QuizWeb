package com.quiz.web.utils;

import java.util.Objects;

public class WebPath {
    String name;
    private boolean isRedirect;
    private WebPageEnum page;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WebPath webPath = (WebPath) o;
        return isRedirect == webPath.isRedirect && Objects.equals(name, webPath.name) && page == webPath.page && dispatchType == webPath.dispatchType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isRedirect, page, dispatchType);
    }

    private DispatchType dispatchType;

    public WebPath(String name, DispatchType dispatchType) {
        this.name = name;
        this.dispatchType = dispatchType;
    }

    public DispatchType getDispatchType() {
        return dispatchType;
    }

    public WebPath setDispatchType(DispatchType dispatchType) {
        this.dispatchType = dispatchType;
        return this;
    }

    public String getName() {
        return name;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public enum DispatchType {FORWARD, REDIRECT, STAND}

    public enum WebPageEnum {
        STAND(""),
        HOME("/home"),
        LOGIN("/login"),
        REGISTER("/register"),
        EDIT_PERSONAL_INFO("/profile/edit"),
        PROFILE("/profile"),
        QUIZ_CREATE("/create"),
        QUIZ_START("/start"),
        ERROR("/error"),
        USER_RESULTS("/profile/results"),
        ADMIN_USERS("/profile/users"),
        QUIZ_EDIT("/edit"),
        QUIZ_END("/finish"),
        USER_EDIT("/user/edit");
        private final String path;

        WebPageEnum(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }
}