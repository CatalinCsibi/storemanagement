package com.example.storemanagement.exception;

public class ProductNotRegisteredException extends RuntimeException{

    public ProductNotRegisteredException(String message, String name) {
        super(String.format(message, name));
    }
}
