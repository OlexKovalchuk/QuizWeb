package com.quiz.exceptions;


public class DangerousQueryDeniedException extends RuntimeException{
    public DangerousQueryDeniedException() {
    }

    public DangerousQueryDeniedException(String message) {
        super(message);
    }

    public DangerousQueryDeniedException(String message, Throwable cause) {
        super(message, cause);
    }
}