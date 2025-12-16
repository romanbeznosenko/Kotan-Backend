package com.kotanapp.kotanappapi.core.login.management;

public class IncorrectLoginCredentialsException extends Exception {
    public IncorrectLoginCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}