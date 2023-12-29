package com.example.storemanagement.exception;

public class ProductAlreadyRegisteredException extends RuntimeException{

    public ProductAlreadyRegisteredException(String message, String name) {
        super(String.format(message, name));
    }
}
