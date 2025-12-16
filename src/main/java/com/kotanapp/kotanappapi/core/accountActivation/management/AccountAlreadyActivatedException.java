package com.kotanapp.kotanappapi.core.accountActivation.management;

public class AccountAlreadyActivatedException extends Exception {
    public AccountAlreadyActivatedException(String errorMessage) {
        super(errorMessage);
    }
}