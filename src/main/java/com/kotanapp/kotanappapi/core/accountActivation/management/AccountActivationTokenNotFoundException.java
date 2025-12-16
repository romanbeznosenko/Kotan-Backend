package com.kotanapp.kotanappapi.core.accountActivation.management;

public class AccountActivationTokenNotFoundException extends Exception {
    public AccountActivationTokenNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}