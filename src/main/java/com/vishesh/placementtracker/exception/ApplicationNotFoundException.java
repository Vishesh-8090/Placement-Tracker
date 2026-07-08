package com.vishesh.placementtracker.exception;

public class ApplicationNotFoundException extends RuntimeException{

    public ApplicationNotFoundException(String message){
        super(message);
    }
}
