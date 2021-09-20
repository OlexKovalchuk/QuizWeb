package com.quiz.controller.utils;

public class WebPath {
    String name;
    private boolean isRedirect;

    private DispatchType dispatchType;

    public WebPath(String name, DispatchType dispatchType) {
        this.name = name;
        this.dispatchType=dispatchType;
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
    public enum DispatchType {FORWARD, REDIRECT}
    public enum  WebPageEnum{
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
        QUIZ_END("/finish");
        private final String path;

        WebPageEnum(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }

    }
}
