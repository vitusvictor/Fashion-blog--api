package com.emmanuela.Fashion_Blog_API.exceptions;

public class CategoryAlreadyExistException extends RuntimeException{
    public CategoryAlreadyExistException(String message) {
        super(message);
    }
}
