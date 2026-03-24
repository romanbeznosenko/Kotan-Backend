package com.kotanapp.kotanappapi.modules.admin.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.team.models.TeamAdminListResponse;
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamSpecifications;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamAdminPageService {
    private final TeamManager teamManager;
    private final ClubManager clubManager;
    private final StorageService storageService;

    public CustomPaginationResponse<TeamAdminListResponse> pageTeams(
            int page, int limit,
            UUID clubId,
            GenderEnum gender,
            AgeGroupEnum ageGroup
    ) {
        log.info("Fetching teams from club with id: {}", clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);

        Specification<TeamDAO> spec = TeamSpecifications.isNotArchived()
                .and(TeamSpecifications.byAgeGroup(ageGroup != null ? List.of(ageGroup) : null))
                .and(TeamSpecifications.byGender(gender != null ? List.of(gender) : null))
                .and(TeamSpecifications.byClub(clubDAO));

        Page<TeamDAO> teamDAOPage = teamManager.findAll(spec, PageRequest.of(page - 1, limit));
        List<TeamAdminListResponse> teamAdminListResponses = teamDAOPage.get()
                .map(teamDAO -> TeamAdminBuilders.buildListResponse(teamDAO, storageService))
                .toList();

        return new CustomPaginationResponse<>(teamAdminListResponses, teamDAOPage.getTotalElements());
    }
}
