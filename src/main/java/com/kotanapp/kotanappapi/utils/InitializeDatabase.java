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
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubMapper;
import com.kotanapp.kotanappapi.modules.club.management.ClubSpecifications;
import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.club.models.ClubId;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamMapper;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamId;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.AuthTypeEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
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
    private final ClubManager clubManager;
    private final ClubMapper clubMapper;
    private final TeamManager teamManager;
    private final TeamMapper teamMapper;

    @PostConstruct
    public void init() {
        UserDAO userDAO = userManager.findUserByEmailAndArchivedFalse("admin@kotan.local")
                .orElse(null);

        if (userDAO == null) {
            log.info("Creating users...");
            createUser("admin@kotan.local", UserTypeEnum.ADMIN);
            createUser("user@kotan.local", UserTypeEnum.USER);
        }

        ClubDAO clubDAO = clubManager.findOne(ClubSpecifications.byName("Kotan Ozorków"))
                .orElse(null);
        if (clubDAO == null) {
            clubDAO = createClub();
            createTeams(clubDAO);
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

    public ClubDAO createClub(){
        log.info("Creating the club...");

        Club club = Club.builder()
                .clubId(ClubId.of(null))
                .shortName("Kotan")
                .name("Kotan Ozorków")
                .city("Ozorków")
                .country("Poland")
                .isOurClub(true)
                .isArchived(false)
                .build();
        ClubDAO clubDAO = clubMapper.mapToEntity(club, new CycleAvoidingMappingContext());
        return clubManager.saveToDatabase(clubDAO);
    }

    public void createTeams(ClubDAO clubDAO){
        log.info("Creating the teams...");

        Club club = clubMapper.mapToDomain(clubDAO, new CycleAvoidingMappingContext());

        Team team = buildTeam(
                "Trampkarz C1",
                AgeGroupEnum.U16_U17,
                GenderEnum.MEN,
                "Ernest Wyderka",
                "Uknown",
                club

        );
        TeamDAO teamDAO = teamMapper.mapToEntity(team, new CycleAvoidingMappingContext());

        teamManager.saveToDatabase(teamDAO);
    }

    private Team buildTeam(
            String name,
            AgeGroupEnum ageGroup,
            GenderEnum gender,
            String coachName,
            String leagueName,
            Club club
    ){
        return Team.builder()
                .teamId(TeamId.of(null))
                .club(club)
                .name(name)
                .ageGroup(ageGroup)
                .gender(gender)
                .coachName(coachName)
                .coverImage(null)
                .description(null)
                .leagueName(leagueName)
                .isArchived(false)
                .build();
    }
}
