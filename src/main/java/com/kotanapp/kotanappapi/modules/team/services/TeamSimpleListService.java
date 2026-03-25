package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamSpecifications;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamSimpleListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamSimpleListService {
    private final ClubManager clubManager;
    private final TeamManager teamManager;

    public List<TeamSimpleListResponse> simpleListTeams(UUID clubId) {
        log.info("Fetching teams from club with id: {}", clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);
        Specification<TeamDAO> spec = TeamSpecifications.isNotArchived()
                .and(TeamSpecifications.byClub(clubDAO));
        List<TeamDAO> teamDAOList = teamManager.findAll(spec);

        return teamDAOList.stream()
                .map(TeamBuilders::buildSimpleListResponse)
                .toList();
    }
}
