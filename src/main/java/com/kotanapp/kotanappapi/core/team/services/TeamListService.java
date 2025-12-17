package com.kotanapp.kotanappapi.core.team.services;

import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.core.team.models.TeamListResponse;
import com.kotanapp.kotanappapi.core.team.models.TeamPageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamListService {
    private final TeamManager teamManager;

    public TeamPageResponse listTeams(int page, int limit){
        log.info("Listing all teams");

        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<TeamDAO> teamDAOPage = teamManager.findAll(pageable);

        List<TeamListResponse> teamListResponses = teamDAOPage.get()
                .map(TeamBuilders::buildListResponse)
                .toList();

        return TeamPageResponse.builder()
                .count(teamDAOPage.getTotalElements())
                .data(teamListResponses)
                .build();
    }
}
