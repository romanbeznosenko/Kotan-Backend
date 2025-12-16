package com.kotanapp.kotanappapi.core.login;

import com.kotanapp.kotanappapi.core.login.management.AccountNotActivatedException;
import com.kotanapp.kotanappapi.core.login.management.IncorrectLoginCredentialsException;
import com.kotanapp.kotanappapi.core.login.models.LoginRequest;
import com.kotanapp.kotanappapi.core.login.services.LoginService;
import com.kotanapp.kotanappapi.core.user.management.UserNotFoundException;
import com.kotanapp.kotanappapi.utils.CustomResponse;
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

    @PostMapping(value = "/auth/login")
    @PreAuthorize("permitAll()")
    @Operation(
            summary = "Login user",
            description = "Login user with provided username and password")
    public ResponseEntity<CustomResponse<Void>> loginUser(
            @Valid
            @RequestBody LoginRequest request) throws UserNotFoundException, IncorrectLoginCredentialsException, AccountNotActivatedException {

        loginService.loginUser(request);

        return new ResponseEntity<>(new CustomResponse<>(null, "Login successful.", HttpStatus.OK),
                                    HttpStatus.OK);
    }
}


