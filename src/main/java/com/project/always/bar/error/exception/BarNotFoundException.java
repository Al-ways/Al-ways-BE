package com.project.always.bar.error.exception;

public class BarNotFoundException extends RuntimeException {
    public BarNotFoundException(String message){
        super(message);
    }

    public BarNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
