package com.kotanapp.kotanappapi.core.accountActivation.services;


import com.kotanapp.kotanappapi.core.accountActivation.management.AccountAlreadyActivatedException;
import com.kotanapp.kotanappapi.core.accountActivation.management.ActivateAccountTokenAlreadyGeneratedException;
import com.kotanapp.kotanappapi.core.accountActivation.management.VerificationCodeManager;
import com.kotanapp.kotanappapi.core.accountActivation.management.VerificationCodeMapper;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCode;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeDAO;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeResendRequest;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountManager;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.core.user.management.UserNotFoundException;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import com.kotanapp.kotanappapi.utils.mailer.ActivationEmailService;
import com.mailgun.exception.MailGunException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class VerificationCodeResendService {
    private final AuthAccountManager authAccountManager;
    private final AuthAccountMapper authAccountMapper;
    private final VerificationCodeManager verificationCodeManager;
    private final VerificationCodeMapper verificationCodeMapper;
    private final VerificationCodeCreateService verificationCodeCreateService;
    private final ActivationEmailService activationEmailService;

    public void resendVerificationCode(VerificationCodeResendRequest request) throws UserNotFoundException, AccountAlreadyActivatedException, ActivateAccountTokenAlreadyGeneratedException {

        log.info("Resending account activation code to user: {}", request.email());

        String trimmedEmail = request.email()
                                     .strip();

        AuthAccountDAO authAccountDAO = authAccountManager.findByEmail(trimmedEmail)
                                                          .orElseThrow(
                                                                  () -> new UserNotFoundException("User not found!"));
        AuthAccount authAccount = authAccountMapper.mapToDomain(authAccountDAO, new CycleAvoidingMappingContext());
        if (Boolean.TRUE.equals(authAccount.getIsActivated()))
            throw new AccountAlreadyActivatedException("User is already activated!");

        VerificationCodeDAO verificationCodeDAO = verificationCodeManager.findVerificationCodeByUser(authAccountDAO)
                                                                         .orElse(null);
        VerificationCode verificationCode = verificationCodeMapper.mapToDomain(verificationCodeDAO,
                                                                               new CycleAvoidingMappingContext());
        long duration = Duration.between(LocalDateTime.now(), verificationCode.getCreatedAt())
                                .toMinutes();
        if (duration < 3)
            throw new ActivateAccountTokenAlreadyGeneratedException(
                    "Account activation token has been generated less than 3 minutes ago! Try again in " + (3 - duration) + " minutes");
        if (verificationCodeDAO != null) {
            verificationCodeManager.deleteVerificationCode(verificationCodeDAO);
        }

        verificationCodeDAO = verificationCodeCreateService.generateVerificationCode(authAccount);

        try {
            activationEmailService.sendActivationEmail(authAccountDAO, verificationCodeDAO.getCode());
        } catch (MailGunException e) {
            verificationCodeManager.deleteVerificationCode(verificationCodeDAO);
            throw e;
        }
        log.info("Resent account activation code to user: {}", request.email());
    }

}

