package com.kotanapp.kotanappapi.core.login.management;

public class AccountNotActivatedException extends Exception {
    public AccountNotActivatedException(String errorMessage) {
        super(errorMessage);
    }
}