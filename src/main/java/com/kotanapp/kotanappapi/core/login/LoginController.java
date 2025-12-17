package com.kotanapp.kotanappapi.core.login;

import com.kotanapp.kotanappapi.core.login.management.AccountBlockedException;
import com.kotanapp.kotanappapi.core.login.management.AccountNotActivatedException;
import com.kotanapp.kotanappapi.core.login.management.IncorrectLoginCredentialsException;
import com.kotanapp.kotanappapi.core.login.models.LoginRequest;
import com.kotanapp.kotanappapi.core.login.models.LoginResponse;
import com.kotanapp.kotanappapi.core.login.models.RefreshTokenRequest;
import com.kotanapp.kotanappapi.core.login.models.RefreshTokenResponse;
import com.kotanapp.kotanappapi.core.login.services.LoginService;
import com.kotanapp.kotanappapi.core.login.services.RefreshTokenService;
import com.kotanapp.kotanappapi.core.user.management.UserNotFoundException;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import com.kotanapp.kotanappapi.utils.jwt.ExpiredJwtException;
import com.kotanapp.kotanappapi.utils.jwt.InvalidJwtException;
import com.kotanapp.kotanappapi.utils.jwt.InvalidTokenTypeException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping(value = "/auth/login")
    @PreAuthorize("permitAll()")
    @Operation(
            summary = "Login user",
            description = "Login user with provided username and password and return JWT tokens")
    public ResponseEntity<CustomResponse<LoginResponse>> loginUser(
            @Valid
            @RequestBody LoginRequest request)
            throws UserNotFoundException, IncorrectLoginCredentialsException,
                   AccountNotActivatedException, AccountBlockedException {

        LoginResponse tokens = loginService.loginUser(request);

        return new ResponseEntity<>(
                new CustomResponse<>(tokens, "Login successful.", HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @PostMapping(value = "/auth/refresh")
    @PreAuthorize("permitAll()")
    @Operation(
            summary = "Refresh access token",
            description = "Use refresh token to obtain new access and refresh tokens")
    public ResponseEntity<CustomResponse<RefreshTokenResponse>> refreshToken(
            @Valid
            @RequestBody RefreshTokenRequest request)
            throws InvalidJwtException, ExpiredJwtException, InvalidTokenTypeException,
                   UserNotFoundException, AccountBlockedException {

        RefreshTokenResponse tokens = refreshTokenService.refreshTokens(request);

        return new ResponseEntity<>(
                new CustomResponse<>(tokens, "Tokens refreshed successfully.", HttpStatus.OK),
                HttpStatus.OK
        );
    }
}


