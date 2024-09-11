package com.example.RewardProject.CustomException;


import org.springframework.web.bind.annotation.ExceptionHandler;

public class CustomConstraintViolationException  extends RuntimeException {
    public CustomConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
