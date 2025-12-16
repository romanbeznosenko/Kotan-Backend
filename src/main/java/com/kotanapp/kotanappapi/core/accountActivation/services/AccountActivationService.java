package com.kotanapp.kotanappapi.core.accountActivation.services;

import com.kotanapp.kotanappapi.core.accountActivation.management.*;
import com.kotanapp.kotanappapi.core.accountActivation.models.AccountActivationRequest;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCode;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeDAO;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeResendRequest;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountManager;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.core.user.management.UserManager;
import com.kotanapp.kotanappapi.core.user.management.UserMapper;
import com.kotanapp.kotanappapi.core.user.management.UserNotFoundException;
import com.kotanapp.kotanappapi.core.user.models.User;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.core.user.services.UserBuilders;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import com.kotanapp.kotanappapi.utils.enums.UserTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountActivationService {
    private final VerificationCodeManager verificationCodeManager;
    private final VerificationCodeMapper verificationCodeMapper;
    private final VerificationCodeResendService verificationCodeResendService;
    private final AuthAccountMapper authAccountMapper;
    private final AuthAccountManager authAccountManager;
    private final UserManager userManager;
    private final UserMapper userMapper;

    public void activateUser(AccountActivationRequest request) throws UserNotFoundException, AccountAlreadyActivatedException, AccountActivationTokenNotFoundException, ActivateAccountTokenExpiredException, ActivateAccountTokenAlreadyGeneratedException {

        log.info("Activating user");
        VerificationCodeDAO verificationCodeDAO = verificationCodeManager.findVerificationCodeByCode(request.code())
                                                                         .orElseThrow(
                                                                                 () -> new AccountActivationTokenNotFoundException(
                                                                                         "Account activation token not found!"));
        VerificationCode verificationCode = verificationCodeMapper.mapToDomain(verificationCodeDAO,
                                                                               new CycleAvoidingMappingContext());
        if (Instant.now()
                   .isAfter(verificationCode.getVerificationCodeExpireAt())) {
            verificationCodeResendService.resendVerificationCode(VerificationCodeResendRequest.builder()
                                                                                              .email(verificationCode.getUser()
                                                                                                                     .getEmail())
                                                                                              .build());
            throw new ActivateAccountTokenExpiredException("Account activation token has expired!");
        }

        AuthAccount authAccount = verificationCode.getUser();

        authAccount.setIsActivated(true);


        verificationCodeManager.deleteVerificationCode(verificationCodeDAO);
        log.info("Activated user");

        log.info("Creating user account");
        User user = UserBuilders.buildUserFromEmail(authAccount.getEmail(), null, null, UserTypeEnum.USER);
        user.setUserType(UserTypeEnum.USER);
        UserDAO userDAO = userMapper.mapToEntity(user, new CycleAvoidingMappingContext());
        userDAO = userManager.saveToDatabase(userDAO);
        authAccount.setUserId(userDAO.getId());
        AuthAccountDAO authAccountDAO = authAccountMapper.mapToEntity(authAccount, new CycleAvoidingMappingContext());
        authAccountManager.saveToDatabase(authAccountDAO);

        log.info("Creating default organization");
    }
}

