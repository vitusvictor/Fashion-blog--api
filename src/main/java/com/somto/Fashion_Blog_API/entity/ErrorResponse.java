package com.somto.Fashion_Blog_API.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorResponse {
    private String message;
    private HttpStatus status;
    private String debugMessage;
    private LocalDateTime time = LocalDateTime.now();
}
