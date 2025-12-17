package com.kotanapp.kotanappapi.core.login.management;

import com.kotanapp.kotanappapi.utils.CustomResponse;
import com.kotanapp.kotanappapi.utils.jwt.ExpiredJwtException;
import com.kotanapp.kotanappapi.utils.jwt.InvalidJwtException;
import com.kotanapp.kotanappapi.utils.jwt.InvalidTokenTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class JwtExceptionHandler {

    @ExceptionHandler(InvalidJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<CustomResponse<Void>> handleInvalidJwt(InvalidJwtException ex) {
        log.error("Invalid JWT: {}", ex.getMessage());
        return new ResponseEntity<>(
                new CustomResponse<>(null, "Invalid token", HttpStatus.UNAUTHORIZED),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<CustomResponse<Void>> handleExpiredJwt(ExpiredJwtException ex) {
        log.error("Expired JWT: {}", ex.getMessage());
        return new ResponseEntity<>(
                new CustomResponse<>(null, "Token expired", HttpStatus.UNAUTHORIZED),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(InvalidTokenTypeException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<CustomResponse<Void>> handleInvalidTokenType(InvalidTokenTypeException ex) {
        log.error("Invalid token type: {}", ex.getMessage());
        return new ResponseEntity<>(
                new CustomResponse<>(null, "Invalid token type", HttpStatus.UNAUTHORIZED),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(io.jsonwebtoken.JwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<CustomResponse<Void>> handleGenericJwtException(io.jsonwebtoken.JwtException ex) {
        log.error("JWT error: {}", ex.getMessage());
        return new ResponseEntity<>(
                new CustomResponse<>(null, "Invalid or malformed token", HttpStatus.UNAUTHORIZED),
                HttpStatus.UNAUTHORIZED
        );
    }
}
