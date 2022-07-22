package com.somto.Fashion_Blog_API.exceptions;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String message) {
        super(message);
    }
}
