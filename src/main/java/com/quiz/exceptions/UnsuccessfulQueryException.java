package com.quiz.exceptions;


public class UnsuccessfulQueryException extends RuntimeException{
    public UnsuccessfulQueryException() {
        super("Unsuccessful SQL query");
    }

    public UnsuccessfulQueryException(String message) {
        super(message);
    }

    public UnsuccessfulQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}