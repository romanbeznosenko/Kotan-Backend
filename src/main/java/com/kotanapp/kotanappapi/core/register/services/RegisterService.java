package com.kotanapp.kotanappapi.core.register.services;

import com.kotanapp.kotanappapi.core.accountActivation.services.VerificationCodeSendService;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountManager;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.core.authAccount.services.AuthAccountBuilders;
import com.kotanapp.kotanappapi.core.register.management.UserAlreadyExistException;
import com.kotanapp.kotanappapi.core.register.models.RegisterRequest;
import com.kotanapp.kotanappapi.core.user.management.UserManager;
import com.kotanapp.kotanappapi.core.user.management.UserMapper;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import com.kotanapp.kotanappapi.utils.enums.AuthTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegisterService {
    private final PasswordEncoder passwordEncoder;
    private final UserManager userManager;
    private final UserMapper userMapper;
    private final AuthAccountManager authAccountManager;
    private final AuthAccountMapper authAccountMapper;
    private final VerificationCodeSendService verificationCodeSendService;

    public void registerUser(RegisterRequest request) throws UserAlreadyExistException {
        log.info("Creating new app user: {}", request.email());
        String trimmedEmail = request.email()
                                     .strip()
                                     .toLowerCase();
        String trimmedPassword = request.password()
                                        .strip();

        AuthAccountDAO authAccountDAO = authAccountManager.findByEmail(trimmedEmail)
                                                          .orElse(null);
        if (authAccountDAO != null && authAccountDAO.getIsActivated())
            throw new UserAlreadyExistException("User already exist!");

        if (authAccountDAO == null) {
            AuthAccount authAccount = AuthAccountBuilders.buildAuthAccount(trimmedEmail,
                                                                           passwordEncoder.encode(trimmedPassword),
                                                                           AuthTypeEnum.EMAIL);
            authAccountDAO = authAccountMapper.mapToEntity(authAccount, new CycleAvoidingMappingContext());
            authAccountDAO = authAccountManager.saveToDatabase(authAccountDAO);
        }

        verificationCodeSendService.sendVerificationCode(authAccountDAO);
        log.info("Created new app user: {}", request.email());
    }
}

