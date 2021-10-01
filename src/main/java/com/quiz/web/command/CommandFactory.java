package com.quiz.web.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

/**
 * CommandFactory processing HttpServletRequest and has method that return Command entity due to the request params
 */
public class CommandFactory {
    private final HttpServletRequest request;
    public CommandFactory(HttpServletRequest request) {
        this.request = new HttpServletRequestWrapper(request);
    }

    /**
     * Defines command due to the request URL
     * @return Command entity
     */
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