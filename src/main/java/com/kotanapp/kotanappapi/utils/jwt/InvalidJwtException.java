package com.kotanapp.kotanappapi.utils.jwt;

public class InvalidJwtException extends RuntimeException {
    public InvalidJwtException(String message) {
        super(message);
    }
}
