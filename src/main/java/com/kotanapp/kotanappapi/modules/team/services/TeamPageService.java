package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamSpecifications;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamListResponse;
import com.kotanapp.kotanappapi.modules.team.models.TeamPageResponse;
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
public class TeamPageService {
    private final TeamManager teamManager;
    private final ClubManager clubManager;

    public TeamPageResponse findAll(UUID clubId) {
        log.info("Getting all teams from club with id: {}", clubId);

        ClubDAO club = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);
        Specification<TeamDAO> spec = TeamSpecifications.byClub(club)
                .and(TeamSpecifications.isNotArchived());
        PageRequest pageRequest = PageRequest.of(0, Integer.MAX_VALUE);

        Page<TeamDAO> teamDAOPage = teamManager.findAll(spec, pageRequest);

        List<TeamListResponse> teamListResponses = teamDAOPage.get()
                .map(TeamBuilders::buildListResponse)
                .toList();

        return TeamPageResponse.builder()
                .count(teamDAOPage.getTotalElements())
                .data(teamListResponses)
                .build();
    }
}
