package com.ja.spring.data.jpa.exception;

public class ModelNotFoundException extends RuntimeException{

    private String message;

    public ModelNotFoundException(String message){
        super(message);
    }
}
