package com.intuit.playerservice.exception;

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(String message, BadCredentialsException e) {
        super(message);
    }
}
