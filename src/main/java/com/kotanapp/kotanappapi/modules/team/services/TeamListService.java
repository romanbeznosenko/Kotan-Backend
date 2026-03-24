package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamSpecifications;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamListResponse;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamListService {
    private final TeamManager teamManager;
    private final StorageService storageService;

    public List<TeamListResponse> getTeamList(List<GenderEnum> genderEnumList, List<AgeGroupEnum> ageGroupEnumList) {
        log.info("Fetching teams...");

        Specification<TeamDAO> spec = TeamSpecifications.isNotArchived()
                .and(TeamSpecifications.byGender(genderEnumList))
                .and(TeamSpecifications.byAgeGroup(ageGroupEnumList));

        List<TeamDAO> teamDAOList = teamManager.findAll(spec);

        return teamDAOList.stream()
                .map(teamDAO -> TeamBuilders.buildListResponse(teamDAO, storageService))
                .toList();
    }
}
