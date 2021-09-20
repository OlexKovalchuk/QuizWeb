package com.quiz.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class CommandFactory {
    private HttpServletRequest request;
    private HttpServletResponse response;
    public CommandFactory(HttpServletRequest request, HttpServletResponse response) {
        this.request = new HttpServletRequestWrapper(request);
        this.response = new HttpServletResponseWrapper(response);
    }

    public Command defineCommand() {
        if(request.getMethod().equals("GET")){
            return CommandHolder.get(getActionUrl());
        }
        return CommandHolder.getPOST(getActionUrl());
    }
    private String getActionUrl(){
        return request.getRequestURI().replaceFirst(request.getContextPath(), "");
    }
}
