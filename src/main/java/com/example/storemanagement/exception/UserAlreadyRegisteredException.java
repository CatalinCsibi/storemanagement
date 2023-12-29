package com.example.storemanagement.exception;

public class UserAlreadyRegisteredException extends RuntimeException{

    public UserAlreadyRegisteredException(String message, String name) {
        super(String.format(message, name));
    }
}

