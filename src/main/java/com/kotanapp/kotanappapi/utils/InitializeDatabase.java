package com.kotanapp.kotanappapi.utils;

import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountManager;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.core.authAccount.services.AuthAccountBuilders;
import com.kotanapp.kotanappapi.core.match.management.MatchManager;
import com.kotanapp.kotanappapi.core.match.models.MatchDAO;
import com.kotanapp.kotanappapi.core.team.management.TeamManager;
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

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
    private final MatchManager matchManager;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");

    public static Instant toInstant(String dateTime, ZoneId zoneId) {
        LocalDateTime localDateTime =
                LocalDateTime.parse(dateTime, FORMATTER);

        return localDateTime.atZone(zoneId).toInstant();
    }

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
            createTeam("Kotan Ozorków", TeamTypeEnum.SENIOR_M);

            log.info("Creating matches...");
            createMatch("MKS Mianów", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "24.08.2025, 14:00", "bł. ks. Michała Oziębłowskiego 1 , 99-300 Kutno");
            createMatch("Kotan Ozorków", "KS ROSA ROSANÓW", TeamTypeEnum.SENIOR_M, "27.08.2025, 18:00", "Leśna 1 , 95-035 Ozorków");
            createMatch("Termy II Poddębice", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "31.08.2025, 19:15", "Byczyna , 99-200 Poddębice");
            createMatch("Kotan Ozorków", "MKP Boruta II Zgierz", TeamTypeEnum.SENIOR_M, "06.09.2025, 15:00", "Leśna 1 , 95-035 Ozorków");
            createMatch("Sarnów", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "13.09.2025, 15:00", "GLKS Sarnów/Dalików (Dalików, Łęczycka 3)");
            createMatch("Kotan Ozorków", "KOBRA LEŹNICA", TeamTypeEnum.SENIOR_M, "20.09.2025, 17:00", "Leśna 1 , 95-035 Ozorków");
            createMatch("Ostrovia Ostrowy", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "28.09.2025, 14:00", "bł. ks. Michała Oziębłowskiego 1 , 99-300 Kutno");
            createMatch("Kotan Ozorków", "Górnik Łęczyca", TeamTypeEnum.SENIOR_M, "04.10.2025, 16:00", "Leśna 1 , 95-035 Ozorków");
            createMatch("Sazan Pęczniew", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "11.10.2025, 15:00", "Świnice Warckie (Świnice Warckie, Szkolna 3)");
            createMatch("Kotan Ozorków", "SAP PARZĘCZEW", TeamTypeEnum.SENIOR_M, "18.10.2025, 15:00", "Leśna 1 , 95-035 Ozorków");
            createMatch("Kolejarz Łódź", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "25.10.2025, 14:30", "Srebrzyńska 95 , 94-203 ŁÓDŹ");
            createMatch("Kotan Ozorków", "Termy Uniejów", TeamTypeEnum.SENIOR_M, "08.11.2025, 14:00", "Leśna 1 , 95-035 Ozorków");
            createMatch("LKS MAGNAT Sierpów", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "15.11.2025, 14:00", "Sierpów 10A , 95-035 Ozorków");
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

    public void createMatch(String homeTeamName, String awayTeamName, TeamTypeEnum teamType, String startTime, String location){
        TeamDAO homeTeam = teamManager.findByNameAndTeamType(homeTeamName, teamType).orElse(null);
        TeamDAO awayTeam = teamManager.findByNameAndTeamType(awayTeamName, teamType).orElse(null);


        if (homeTeam != null && awayTeam != null){
            MatchDAO matchDAO = MatchDAO.builder()
                    .homeTeam(homeTeam)
                    .awayTeam(awayTeam)
                    .startTime(toInstant(startTime))
                    .location(location)
                    .isFinished(false)
                    .isArchived(false)
                    .build();

            matchManager.saveToDatabase(matchDAO);
        }
    }

    private static Instant toInstant(String dateTime) {
        return toInstant(dateTime, ZoneId.systemDefault());
    }
}
