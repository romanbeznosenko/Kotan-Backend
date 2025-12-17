package com.kotanapp.kotanappapi.utils;

import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountManager;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.core.authAccount.services.AuthAccountBuilders;
import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.models.Team;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.core.user.management.UserManager;
import com.kotanapp.kotanappapi.core.user.management.UserMapper;
import com.kotanapp.kotanappapi.core.user.models.User;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.core.user.services.UserBuilders;
import com.kotanapp.kotanappapi.utils.enums.AuthTypeEnum;
import com.kotanapp.kotanappapi.utils.enums.TeamTypeEnum;
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
    private final TeamManager teamManager;

    @PostConstruct
    public void init() {
        UserDAO userDAO = userManager.findUserByEmailAndArchivedFalse("admin@kotan.local")
                .orElse(null);

        if (userDAO == null) {
            log.info("Creating users...");
            createUser("admin@kotan.local", UserTypeEnum.ADMIN);
            createUser("user@kotan.local", UserTypeEnum.USER);

            log.info("Creating teams...");
            createTeam("MKP Boruta II Zgierz", TeamTypeEnum.SENIOR_M);
            createTeam("Termy II Poddębice", TeamTypeEnum.SENIOR_M);
            createTeam("Sazan Pęczniew", TeamTypeEnum.SENIOR_M);
            createTeam("KS ROSA ROSANÓW", TeamTypeEnum.SENIOR_M);
            createTeam("KOBRA LEŹNICA", TeamTypeEnum.SENIOR_M);
            createTeam("SAP PARZĘCZEW", TeamTypeEnum.SENIOR_M);
            createTeam("Termy Uniejów", TeamTypeEnum.SENIOR_M);
            createTeam("Kolejarz Łódź", TeamTypeEnum.SENIOR_M);
            createTeam("MKS Mianów",  TeamTypeEnum.SENIOR_M);
            createTeam("LKS MAGNAT Sierpów",  TeamTypeEnum.SENIOR_M);
            createTeam("Sarnów", TeamTypeEnum.SENIOR_M);
            createTeam("Górnik Łęczyca", TeamTypeEnum.SENIOR_M);
            createTeam("Ostrovia Ostrowy", TeamTypeEnum.SENIOR_M);
        }
    }

    public void createUser(String email, UserTypeEnum userType){
        User user = UserBuilders.buildUserFromEmail(
                email,
                "Jan",
                "Kowalski",
                userType
        );

        UserDAO userDAO = userMapper.mapToEntity(user, new CycleAvoidingMappingContext());
        userDAO = userManager.saveToDatabase(userDAO);

        AuthAccount authAccount = AuthAccountBuilders.buildAuthAccount(
                email,
                passwordEncoder.encode("admin"),
                AuthTypeEnum.EMAIL
        );
        AuthAccountDAO authAccountDAO = authAccountMapper.mapToEntity(authAccount, new CycleAvoidingMappingContext());
        authAccountDAO.setUserId(userDAO.getId());
        authAccountDAO.setIsActivated(true);
        authAccountManager.saveToDatabase(authAccountDAO);
    }

    public void createTeam(String teamName, TeamTypeEnum teamType){
        TeamDAO teamDAO = TeamDAO.builder()
                .name(teamName)
                .logo(null)
                .teamType(teamType)
                .isArchived(false)
                .build();

        teamManager.saveToDatabase(teamDAO);
    }
}
