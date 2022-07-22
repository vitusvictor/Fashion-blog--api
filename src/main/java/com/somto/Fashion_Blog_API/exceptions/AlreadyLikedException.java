package com.somto.Fashion_Blog_API.exceptions;

public class AlreadyLikedException extends RuntimeException{
    public AlreadyLikedException(String message) {
        super(message);
    }
}
