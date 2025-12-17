package com.kotanapp.kotanappapi.utils;

import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountManager;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.core.authAccount.services.AuthAccountBuilders;
import com.kotanapp.kotanappapi.core.match.management.MatchManager;
import com.kotanapp.kotanappapi.core.match.models.MatchDAO;
import com.kotanapp.kotanappapi.core.player.management.PlayerManager;
import com.kotanapp.kotanappapi.core.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.core.user.management.UserManager;
import com.kotanapp.kotanappapi.core.user.management.UserMapper;
import com.kotanapp.kotanappapi.core.user.models.User;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.core.user.services.UserBuilders;
import com.kotanapp.kotanappapi.utils.enums.AuthTypeEnum;
import com.kotanapp.kotanappapi.utils.enums.PlayerPositionEnum;
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
    private final PlayerManager playerManager;

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

            createTeam("AKS SMS II ŁÓDŹ", TeamTypeEnum.SENIOR_W);
            createTeam("KKS Włókniarz Konstantynów", TeamTypeEnum.SENIOR_W);
            createTeam("Widzew Łódź SA", TeamTypeEnum.SENIOR_W);
            createTeam("PTC II Pabianice", TeamTypeEnum.SENIOR_W);
            createTeam("KKS Olimpia Karsznice", TeamTypeEnum.SENIOR_W);
            createTeam("Kotan Ozorków", TeamTypeEnum.SENIOR_W);
            createTeam("GKS KORONA Stary Dwór", TeamTypeEnum.SENIOR_W);

            log.info("Creating matches...");
            createMatch("MKS Mianów", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "24.08.2025, 14:00", "bł. ks. Michała Oziębłowskiego 1 , 99-300 Kutno", "6:2");
            createMatch("Kotan Ozorków", "KS ROSA ROSANÓW", TeamTypeEnum.SENIOR_M, "27.08.2025, 18:00", "Leśna 1 , 95-035 Ozorków", "1:4");
            createMatch("Termy II Poddębice", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "31.08.2025, 19:15", "Byczyna , 99-200 Poddębice", "6:1");
            createMatch("Kotan Ozorków", "MKP Boruta II Zgierz", TeamTypeEnum.SENIOR_M, "06.09.2025, 15:00", "Leśna 1 , 95-035 Ozorków", "2:16");
            createMatch("Sarnów", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "13.09.2025, 15:00", "GLKS Sarnów/Dalików (Dalików, Łęczycka 3)", "1:1");
            createMatch("Kotan Ozorków", "KOBRA LEŹNICA", TeamTypeEnum.SENIOR_M, "20.09.2025, 17:00", "Leśna 1 , 95-035 Ozorków", "1:1");
            createMatch("Ostrovia Ostrowy", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "28.09.2025, 14:00", "bł. ks. Michała Oziębłowskiego 1 , 99-300 Kutno", "1:0");
            createMatch("Kotan Ozorków", "Górnik Łęczyca", TeamTypeEnum.SENIOR_M, "04.10.2025, 16:00", "Leśna 1 , 95-035 Ozorków", "1:1");
            createMatch("Sazan Pęczniew", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "11.10.2025, 15:00", "Świnice Warckie (Świnice Warckie, Szkolna 3)", "10:1");
            createMatch("Kotan Ozorków", "SAP PARZĘCZEW", TeamTypeEnum.SENIOR_M, "18.10.2025, 15:00", "Leśna 1 , 95-035 Ozorków", "2:6");
            createMatch("Kolejarz Łódź", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "25.10.2025, 14:30", "Srebrzyńska 95 , 94-203 ŁÓDŹ", "5:1");
            createMatch("Kotan Ozorków", "Termy Uniejów", TeamTypeEnum.SENIOR_M, "08.11.2025, 14:00", "Leśna 1 , 95-035 Ozorków", "0:0");
            createMatch("LKS MAGNAT Sierpów", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "15.11.2025, 14:00", "Sierpów 10A , 95-035 Ozorków", "1:0");

            createMatch("Kotan Ozorków", "KKS Olimpia Karsznice", TeamTypeEnum.SENIOR_W, "14.09.2025, 14:00", "Leśna 1 , 95-035 Ozorków", "2:5");
            createMatch("Kotan Ozorków", "GKS KORONA Stary Dwór", TeamTypeEnum.SENIOR_W, "21.09.2025, 11:00", "Leśna 1 , 95-035 Ozorków", "3:1");
            createMatch("KKS Włókniarz Konstantynów", "Kotan Ozorków", TeamTypeEnum.SENIOR_W, "28.09.2025, 11:00", "Wolności 60 , 95-050 Konstantynów", "2:2");
            createMatch("Kotan Ozorków", "Widzew Łódź SA", TeamTypeEnum.SENIOR_W, "05.10.2025, 15:30", "Leśna 1 , 95-035 Ozorków", "1:4");
            createMatch("AKS SMS II ŁÓDŹ", "Kotan Ozorków", TeamTypeEnum.SENIOR_W, "11.10.2025, 17:00", "Milionowa 12 , 93-193 Łódź", "16:0");
            createMatch("PTC II Pabianice", "Kotan Ozorków", TeamTypeEnum.SENIOR_W, "25.10.2025, 12:30", "gen. Stefana \"Grota\" Roweckiego 3 , 95-200 Pabianice", "9:0");

            log.info("Creating players...");
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Zuzanna", "Andrzejczak", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Wiktoria", "Bartczak", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Zuzia", "Chilarska", PlayerPositionEnum.STRIKER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Wiktoria", "Domańska", PlayerPositionEnum.MIDFIELDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Lena", "Gawęda", PlayerPositionEnum.GOALKEEPER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Zuzanna", "Gibka", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Nadia", "Janasiak", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Amelia", "Marczak", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Patrycja", "Mikołajczyk", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Edyta", "Pietrzak", PlayerPositionEnum.MIDFIELDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Maria", "Rudnicka", PlayerPositionEnum.MIDFIELDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Maria", "Zamolska", PlayerPositionEnum.MIDFIELDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Wiktoria", "Izydorczyk", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Julia", "Malinowska", PlayerPositionEnum.MIDFIELDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Pola", "Łuczak", PlayerPositionEnum.MIDFIELDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Nina", "Strzelecka", PlayerPositionEnum.STRIKER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Yuliia", "Luzhetska", PlayerPositionEnum.GOALKEEPER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Wiktoria", "Pawlak", PlayerPositionEnum.MIDFIELDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Róża", "Pawlak", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Karolina", "Gapsa", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Julianna", "Stańczyk", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Ewa", "Magdziarz", PlayerPositionEnum.DEFENDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Adrianna", "Kurzawska", PlayerPositionEnum.STRIKER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Weronika", "Wiktorowska", PlayerPositionEnum.MIDFIELDER);
            createPlayer("Kotan Ozorków", TeamTypeEnum.SENIOR_W, "Wiktoria", "Łuczak", PlayerPositionEnum.DEFENDER);
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

    public void createMatch(String homeTeamName, String awayTeamName, TeamTypeEnum teamType, String startTime, String location, String result){
        TeamDAO homeTeam = teamManager.findByNameAndTeamType(homeTeamName, teamType).orElse(null);
        TeamDAO awayTeam = teamManager.findByNameAndTeamType(awayTeamName, teamType).orElse(null);


        if (homeTeam != null && awayTeam != null){
            MatchDAO matchDAO = MatchDAO.builder()
                    .homeTeam(homeTeam)
                    .awayTeam(awayTeam)
                    .startTime(toInstant(startTime))
                    .location(location)
                    .isFinished(result != null)
                    .result(result)
                    .isArchived(false)
                    .build();

            matchManager.saveToDatabase(matchDAO);
        }
    }

    private static Instant toInstant(String dateTime) {
        return toInstant(dateTime, ZoneId.systemDefault());
    }

    private void createPlayer(
            String teamName,
            TeamTypeEnum teamType,
            String firstName,
            String lastName,
            PlayerPositionEnum playerPosition
    ){
        TeamDAO teamDAO = teamManager.findByNameAndTeamType(teamName, teamType).orElse(null);

        if (teamDAO != null){
            PlayerDAO playerDAO = PlayerDAO.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .avatar(null)
                    .playerPosition(playerPosition)
                    .team(teamDAO)
                    .isArchived(false)
                    .build();

            playerManager.saveToDatabase(playerDAO);
        }
    }
}
