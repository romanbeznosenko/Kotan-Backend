package com.kotanapp.kotanappapi.core.accountActivation;

import com.kotanapp.kotanappapi.core.accountActivation.management.AccountActivationTokenNotFoundException;
import com.kotanapp.kotanappapi.core.accountActivation.management.AccountAlreadyActivatedException;
import com.kotanapp.kotanappapi.core.accountActivation.management.ActivateAccountTokenAlreadyGeneratedException;
import com.kotanapp.kotanappapi.core.accountActivation.management.ActivateAccountTokenExpiredException;
import com.kotanapp.kotanappapi.core.accountActivation.models.AccountActivationRequest;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeResendRequest;
import com.kotanapp.kotanappapi.core.accountActivation.services.AccountActivationService;
import com.kotanapp.kotanappapi.core.accountActivation.services.VerificationCodeResendService;
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
public class AccountActivateController {
    private final AccountActivationService accountActivationService;
    private final VerificationCodeResendService verificationCodeResendService;

    @PostMapping(value = "/auth/activate")
    @PreAuthorize("permitAll()")
    @Operation(
            summary = "Activate user account",
            description = "Activate user account")
    public ResponseEntity<CustomResponse<String>> activateUserAccount(
            @Valid
            @RequestBody AccountActivationRequest request) throws UserNotFoundException, AccountAlreadyActivatedException, ActivateAccountTokenAlreadyGeneratedException, AccountActivationTokenNotFoundException, ActivateAccountTokenExpiredException {

        accountActivationService.activateUser(request);
        return new ResponseEntity<>(new CustomResponse<>(null, "Account activated.", HttpStatus.OK), HttpStatus.OK);

    }

    @PostMapping(value = "/auth/activate/resend")
    @PreAuthorize("permitAll()")
    @Operation(
            summary = "Resend activation code",
            description = "Resend user account activation code")
    public ResponseEntity<CustomResponse<String>> resendAccountActivationEmail(
            @Valid
            @RequestBody VerificationCodeResendRequest request) throws UserNotFoundException, AccountAlreadyActivatedException, ActivateAccountTokenAlreadyGeneratedException {
        verificationCodeResendService.resendVerificationCode(request);
        return new ResponseEntity<>(new CustomResponse<>(null, "Account activation code resent.", HttpStatus.OK),
                                    HttpStatus.OK);

    }
}


