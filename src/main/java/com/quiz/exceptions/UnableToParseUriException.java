package com.quiz.exceptions;

public class UnableToParseUriException extends RuntimeException{
    public UnableToParseUriException() {
        super();
    }

    public UnableToParseUriException(String message) {
        super(message);
    }

    public UnableToParseUriException(String message, Throwable cause) {
        super(message, cause);
    }
}