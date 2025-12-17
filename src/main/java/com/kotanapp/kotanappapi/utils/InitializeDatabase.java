package com.kotanapp.kotanappapi.utils;

import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountManager;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.core.authAccount.services.AuthAccountBuilders;
import com.kotanapp.kotanappapi.core.user.management.UserManager;
import com.kotanapp.kotanappapi.core.user.management.UserMapper;
import com.kotanapp.kotanappapi.core.user.models.User;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.core.user.services.UserBuilders;
import com.kotanapp.kotanappapi.utils.enums.AuthTypeEnum;
import com.kotanapp.kotanappapi.utils.enums.UserTypeEnum;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InitializeDatabase {
    private final AuthAccountManager authAccountManager;
    private final AuthAccountMapper authAccountMapper;
    private final UserManager userManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        UserDAO userDAO = userManager.findUserByEmailAndArchivedFalse("admin@kotan.local")
                .orElse(null);

        if (userDAO == null) {
            createUser();
        }
    }

    public void createUser(){
        User user = UserBuilders.buildUserFromEmail(
                "admin@kotan.local",
                "Jan",
                "Kowalski",
                UserTypeEnum.ADMIN
        );

        UserDAO userDAO = userMapper.mapToEntity(user, new CycleAvoidingMappingContext());
        userDAO = userManager.saveToDatabase(userDAO);

        AuthAccount authAccount = AuthAccountBuilders.buildAuthAccount(
                "admin@kotan.local",
                passwordEncoder.encode("admin"),
                AuthTypeEnum.EMAIL
        );
        AuthAccountDAO authAccountDAO = authAccountMapper.mapToEntity(authAccount, new CycleAvoidingMappingContext());
        authAccountDAO.setUserId(userDAO.getId());
        authAccountDAO.setIsActivated(true);
        authAccountManager.saveToDatabase(authAccountDAO);
    }
}
