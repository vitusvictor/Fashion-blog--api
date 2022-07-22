package com.somto.Fashion_Blog_API.exceptions;

public class PermissionDeniedException extends RuntimeException{
    public PermissionDeniedException(String message) {
        super(message);
    }
}
