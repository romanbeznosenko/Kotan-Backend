package com.kotanapp.kotanappapi.core.accountActivation.management;

public class ActivateAccountTokenAlreadyGeneratedException extends Exception {
    public ActivateAccountTokenAlreadyGeneratedException(String errorMessage) {
        super(errorMessage);
    }
}