package com.kotanapp.kotanappapi.utils.jwt;

public class ExpiredJwtException extends RuntimeException {
    public ExpiredJwtException(String message) {
        super(message);
    }
}
