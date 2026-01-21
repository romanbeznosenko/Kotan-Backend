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
//            createTeam("MKP Boruta II Zgierz", TeamTypeEnum.SENIOR_M);
//            createTeam("Termy II Poddębice", TeamTypeEnum.SENIOR_M);
//            createTeam("Sazan Pęczniew", TeamTypeEnum.SENIOR_M);
//            createTeam("KS ROSA ROSANÓW", TeamTypeEnum.SENIOR_M);
//            createTeam("KOBRA LEŹNICA", TeamTypeEnum.SENIOR_M);
//            createTeam("SAP PARZĘCZEW", TeamTypeEnum.SENIOR_M);
//            createTeam("Termy Uniejów", TeamTypeEnum.SENIOR_M);
//            createTeam("Kolejarz Łódź", TeamTypeEnum.SENIOR_M);
//            createTeam("MKS Mianów",  TeamTypeEnum.SENIOR_M);
//            createTeam("LKS MAGNAT Sierpów",  TeamTypeEnum.SENIOR_M);
//            createTeam("Sarnów", TeamTypeEnum.SENIOR_M);
//            createTeam("Górnik Łęczyca", TeamTypeEnum.SENIOR_M);
//            createTeam("Ostrovia Ostrowy", TeamTypeEnum.SENIOR_M);
            createTeam("Kotan Ozorków", TeamTypeEnum.SENIOR_M);

//            createTeam("AKS SMS II ŁÓDŹ", TeamTypeEnum.SENIOR_W);
//            createTeam("KKS Włókniarz Konstantynów", TeamTypeEnum.SENIOR_W);
//            createTeam("Widzew Łódź SA", TeamTypeEnum.SENIOR_W);
//            createTeam("PTC II Pabianice", TeamTypeEnum.SENIOR_W);
//            createTeam("KKS Olimpia Karsznice", TeamTypeEnum.SENIOR_W);
            createTeam("Kotan Ozorków", TeamTypeEnum.SENIOR_W);
//            createTeam("GKS KORONA Stary Dwór", TeamTypeEnum.SENIOR_W);

//            log.info("Creating matches...");
//            createMatch("MKS Mianów", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "24.08.2025, 14:00", "bł. ks. Michała Oziębłowskiego 1 , 99-300 Kutno", "6:2");
//            createMatch("Kotan Ozorków", "KS ROSA ROSANÓW", TeamTypeEnum.SENIOR_M, "27.08.2025, 18:00", "Leśna 1 , 95-035 Ozorków", "1:4");
//            createMatch("Termy II Poddębice", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "31.08.2025, 19:15", "Byczyna , 99-200 Poddębice", "6:1");
//            createMatch("Kotan Ozorków", "MKP Boruta II Zgierz", TeamTypeEnum.SENIOR_M, "06.09.2025, 15:00", "Leśna 1 , 95-035 Ozorków", "2:16");
//            createMatch("Sarnów", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "13.09.2025, 15:00", "GLKS Sarnów/Dalików (Dalików, Łęczycka 3)", "1:1");
//            createMatch("Kotan Ozorków", "KOBRA LEŹNICA", TeamTypeEnum.SENIOR_M, "20.09.2025, 17:00", "Leśna 1 , 95-035 Ozorków", "1:1");
//            createMatch("Ostrovia Ostrowy", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "28.09.2025, 14:00", "bł. ks. Michała Oziębłowskiego 1 , 99-300 Kutno", "1:0");
//            createMatch("Kotan Ozorków", "Górnik Łęczyca", TeamTypeEnum.SENIOR_M, "04.10.2025, 16:00", "Leśna 1 , 95-035 Ozorków", "1:1");
//            createMatch("Sazan Pęczniew", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "11.10.2025, 15:00", "Świnice Warckie (Świnice Warckie, Szkolna 3)", "10:1");
//            createMatch("Kotan Ozorków", "SAP PARZĘCZEW", TeamTypeEnum.SENIOR_M, "18.10.2025, 15:00", "Leśna 1 , 95-035 Ozorków", "2:6");
//            createMatch("Kolejarz Łódź", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "25.10.2025, 14:30", "Srebrzyńska 95 , 94-203 ŁÓDŹ", "5:1");
//            createMatch("Kotan Ozorków", "Termy Uniejów", TeamTypeEnum.SENIOR_M, "08.11.2025, 14:00", "Leśna 1 , 95-035 Ozorków", "0:0");
//            createMatch("LKS MAGNAT Sierpów", "Kotan Ozorków", TeamTypeEnum.SENIOR_M, "15.11.2025, 14:00", "Sierpów 10A , 95-035 Ozorków", "1:0");
//
//            createMatch("Kotan Ozorków", "KKS Olimpia Karsznice", TeamTypeEnum.SENIOR_W, "14.09.2025, 14:00", "Leśna 1 , 95-035 Ozorków", "2:5");
//            createMatch("Kotan Ozorków", "GKS KORONA Stary Dwór", TeamTypeEnum.SENIOR_W, "21.09.2025, 11:00", "Leśna 1 , 95-035 Ozorków", "3:1");
//            createMatch("KKS Włókniarz Konstantynów", "Kotan Ozorków", TeamTypeEnum.SENIOR_W, "28.09.2025, 11:00", "Wolności 60 , 95-050 Konstantynów", "2:2");
//            createMatch("Kotan Ozorków", "Widzew Łódź SA", TeamTypeEnum.SENIOR_W, "05.10.2025, 15:30", "Leśna 1 , 95-035 Ozorków", "1:4");
//            createMatch("AKS SMS II ŁÓDŹ", "Kotan Ozorków", TeamTypeEnum.SENIOR_W, "11.10.2025, 17:00", "Milionowa 12 , 93-193 Łódź", "16:0");
//            createMatch("PTC II Pabianice", "Kotan Ozorków", TeamTypeEnum.SENIOR_W, "25.10.2025, 12:30", "gen. Stefana \"Grota\" Roweckiego 3 , 95-200 Pabianice", "9:0");

            log.info("Creating players...");
            createPlayer(TeamTypeEnum.SENIOR_W, "Zuzanna", "Andrzejczak", PlayerPositionEnum.DEFENDER);
            createPlayer(TeamTypeEnum.SENIOR_W, "Wiktoria", "Bartczak", PlayerPositionEnum.DEFENDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Zuzia", "Chilarska", PlayerPositionEnum.STRIKER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Wiktoria", "Domańska", PlayerPositionEnum.MIDFIELDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Lena", "Gawęda", PlayerPositionEnum.GOALKEEPER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Zuzanna", "Gibka", PlayerPositionEnum.DEFENDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Nadia", "Janasiak", PlayerPositionEnum.DEFENDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Amelia", "Marczak", PlayerPositionEnum.MIDFIELDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Patrycja", "Mikołajczyk", PlayerPositionEnum.DEFENDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Edyta", "Pietrzak", PlayerPositionEnum.MIDFIELDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Maria", "Rudnicka", PlayerPositionEnum.MIDFIELDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Maria", "Zamolska", PlayerPositionEnum.MIDFIELDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Wiktoria", "Izydorczyk", PlayerPositionEnum.DEFENDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Julia", "Malinowska", PlayerPositionEnum.MIDFIELDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Pola", "Łuczak", PlayerPositionEnum.MIDFIELDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Nina", "Strzelecka", PlayerPositionEnum.STRIKER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Yuliia", "Luzhetska", PlayerPositionEnum.GOALKEEPER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Wiktoria", "Pawlak", PlayerPositionEnum.MIDFIELDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Róża", "Pawlak", PlayerPositionEnum.DEFENDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Karolina", "Gapsa", PlayerPositionEnum.DEFENDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Julianna", "Stańczyk", PlayerPositionEnum.GOALKEEPER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Ewa", "Magdziarz", PlayerPositionEnum.DEFENDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Adrianna", "Kurzawska", PlayerPositionEnum.STRIKER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Weronika", "Wiktorowska", PlayerPositionEnum.MIDFIELDER);
            createPlayer( TeamTypeEnum.SENIOR_W, "Wiktoria", "Łuczak", PlayerPositionEnum.DEFENDER);

            createPlayer(TeamTypeEnum.JUNIOR_W, "Nikola", "Abramczyk", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Lena", "Barańska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Maria", "Barylska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Oliwia", "Dymińska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Ida", "Gajda", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Nina", "Karolak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Barbara", "Kowalska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Zuzia", "Lubińska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Maria", "Sadok", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Malwina", "Sypniewska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Magda", "Wróbel", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Iga", "Szubert", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Patrycja", "Mikołajczyk", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Zuzanna", "Dąbrowska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Nina", "Strzelecka", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.JUNIOR_W, "Julianna", "Stańczyk", PlayerPositionEnum.STRIKER);


            createPlayer(TeamTypeEnum.MLODZIK_M, "Stanisław", "Bednarski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Filip", "Klimczak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Hubert", "Kurlapski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Robert", "Maciejewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Antoni", "Malinowski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Adam", "Pęśko", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Jakub", "Pietruszewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Antoni", "Sęk", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Jakub", "Siubielski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Bronislaw", "Stasiak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Adam", "Tomczak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Adam", "Wójcicki", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Franciszek", "Wróbel", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.MLODZIK_M, "Norbert", "Wymysłowski", PlayerPositionEnum.STRIKER);

            createPlayer(TeamTypeEnum.ORLIK_M, "Franciszek", "Bzowski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Fabian", "Chmieliński", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Franciszek", "Kierończak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Natan", "Kopka", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Natan", "Maciejewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Aleksander", "Matusiak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Mikołaj", "Rechtanek", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Dominik", "Rogala", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Michał", "Trella", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Wincent", "Wachowicz", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Maciej", "Wojciechowski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Antoni", "Wójcicki", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Tymoteusz", "Wójcicki", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLIK_M, "Jan", "Kacprzak", PlayerPositionEnum.STRIKER);

            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Tymoteusz", "Gryc", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Ksawery", "Izydorczyk", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Julian", "Jabłoński", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Filip", "Kmieciak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Jakub", "Kowalczyk", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Wojciech", "Lubczyński", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Karol", "Marczewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Michał", "Marczewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Adrian", "Moruzgała", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Szymon", "Mroziak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Hubert", "Olczak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Miłosz", "Olczak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Miłosz", "Pisera", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Milan", "Plewiński", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Jakub", "Raszewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Dawid", "Rogala", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Władysław", "Rybski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Aleksander", "Rzeźniczak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Bartłomiej", "Sajewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Łukasz", "Strzałka", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Igor", "Tomczak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Igor", "Waliszewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Piotr", "Wojna", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.TRAMPKARZ_M, "Antoni", "Józefowicz", PlayerPositionEnum.STRIKER);

            createPlayer(TeamTypeEnum.ZAK_M, "Zofia", "Derulska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Antoni", "Grabowski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Szymon", "Jankowiak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Jan", "Kwiatkowski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Natan", "Miniak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Franciszek", "Pietruszewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Wojciech", "Pietruszewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Aleksander", "Rechtanek", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Dawid", "Rybacki", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Tymoteusz", "Rybnik", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Marcel", "Schlüter", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Adam", "Skonieczny", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Szymon", "Sowiński", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Iga", "Szubert", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Julian", "Szubert", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Wojciech", "Terebiński", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ZAK_M, "Karol", "Trzciński", PlayerPositionEnum.STRIKER);

            createPlayer(TeamTypeEnum.SENIOR_M, "Roman", "Beznosenko", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Radosław", "Dąbrowski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Jakub", "Dorcz", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Łukasz", "Dwornicki", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Dawid", "Gajda", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Kacper", "Hajduk", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Artur", "Ignaczak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Patryk", "Jakóbiak", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Fabian", "Jaroszewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Przemysław", "Kołodziejczyk", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Tomasz", "Kołodziejczyk", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Krzysztof", "Kopka", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Arkadiusz", "Muszyński", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Piotr", "Osinski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Bartłomiej", "Rybacki", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Jakub", "Sibilski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Szymon", "Smakowski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Dawid", "Stelmaszewski", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Dawid", "Surdy", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Adrian", "Wójcicki", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Klaudiusz", "Zając", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Mateusz", "Jabłoński", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.SENIOR_M, "Brandon", "Gielnik", PlayerPositionEnum.STRIKER);

            createPlayer(TeamTypeEnum.ORLICZKI_W, "Lena", "Barańska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Maria", "Barylska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Ida", "Gajda", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Joanna", "Mikołajczyk", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Malwina", "Sypniewska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Magda", "Wróbel", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Iga", "Szubert", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Brygida", "Wachowicz", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Anhelina", "Kit", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Julia", "Kwiatkowska", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Amelia", "Horna", PlayerPositionEnum.STRIKER);
            createPlayer(TeamTypeEnum.ORLICZKI_W, "Aleksandra", "Barylska", PlayerPositionEnum.STRIKER);

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
//            String teamName,
            TeamTypeEnum teamType,
            String firstName,
            String lastName,
            PlayerPositionEnum playerPosition
    ){
        TeamDAO teamDAO = teamManager.findByNameAndTeamType("Kotan Ozorków", teamType).orElse(null);

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
