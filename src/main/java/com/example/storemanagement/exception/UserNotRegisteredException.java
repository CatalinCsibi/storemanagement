package com.example.storemanagement.exception;

public class UserNotRegisteredException extends RuntimeException{

    public UserNotRegisteredException(String message, String name) {
        super(String.format(message, name));
    }
}
