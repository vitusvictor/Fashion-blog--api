package com.somto.Fashion_Blog_API.exceptions;

public class PostsNotFoundException extends RuntimeException{
    public PostsNotFoundException(String message) {
        super(message);
    }
}
