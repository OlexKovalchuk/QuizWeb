package com.quiz.controller.utils;

public class ErrorMessage {

    private String redirectUrl;
    private String type;
    private String header;
    private String description;
    public String buttonName;

    public ErrorMessage() {
        redirectUrl = "/Quiz_war_exploded/quiz/";
        type="404";
        header="Look like you're lost";
        description ="the page you are looking is not available for!";
        buttonName="Go to Home";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

}
