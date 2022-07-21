package com.emmanuela.Fashion_Blog_API.exceptions;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException(String message) {
        super(message);
    }
}
