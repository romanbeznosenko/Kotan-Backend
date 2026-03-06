package com.kotanapp.kotanappapi.core.register;

import com.kotanapp.kotanappapi.core.register.management.UserAlreadyExistException;
import com.kotanapp.kotanappapi.core.register.models.RegisterRequest;
import com.kotanapp.kotanappapi.core.register.services.RegisterService;
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
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping(value = "/auth/register")
    @PreAuthorize("permitAll()")
    @Operation(
            summary = "Fuck you ",
            description = "Register user with provided username and password")
    public ResponseEntity<CustomResponse<String>> registerUser(
            @Valid
            @RequestBody RegisterRequest request) throws UserAlreadyExistException {
        registerService.registerUser(request);
        return new ResponseEntity<>(new CustomResponse<>(null, "User created.", HttpStatus.CREATED),
                                    HttpStatus.CREATED);
    }
}


