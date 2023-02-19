package com.spring.unittest.service.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User Not Found!");
    }
}