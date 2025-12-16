package com.kotanapp.kotanappapi.core.accountActivation.services;

import com.kotanapp.kotanappapi.core.accountActivation.management.VerificationCodeManager;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeDAO;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountManager;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import com.kotanapp.kotanappapi.utils.mailer.ActivationEmailService;
import com.mailgun.exception.MailGunException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VerificationCodeSendService {
    private final VerificationCodeCreateService verificationCodeCreateService;
    private final VerificationCodeManager verificationCodeManager;
    private final ActivationEmailService activationEmailService;
    private final AuthAccountManager authAccountManager;
    private final AuthAccountMapper authAccountMapper;

    public void sendVerificationCode(AuthAccountDAO authAccountDAO) {
        log.info("Sending account activation code to user: {}", authAccountDAO.getEmail());

        VerificationCodeDAO verificationCodeDAO = verificationCodeCreateService.generateVerificationCode(
                authAccountMapper.mapToDomain(authAccountDAO, new CycleAvoidingMappingContext()));
        try {
            activationEmailService.sendActivationEmail(authAccountDAO, verificationCodeDAO.getCode());
        } catch (MailGunException e) {
            verificationCodeManager.deleteVerificationCode(verificationCodeDAO);
            authAccountManager.deleteAuthAccount(authAccountDAO);
            throw e;
        }

        log.info("Sent account activation code to user: {}", authAccountDAO.getEmail());
    }

}

