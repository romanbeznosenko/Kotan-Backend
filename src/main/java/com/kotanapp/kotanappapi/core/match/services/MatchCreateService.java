package com.kotanapp.kotanappapi.core.match.services;

import com.kotanapp.kotanappapi.core.match.management.MatchManager;
import com.kotanapp.kotanappapi.core.match.management.MatchMapper;
import com.kotanapp.kotanappapi.core.match.models.Match;
import com.kotanapp.kotanappapi.core.match.models.MatchDAO;
import com.kotanapp.kotanappapi.core.match.models.MatchRequest;
import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchCreateService {
    private final TeamManager teamManager;
    private final MatchManager matchManager;
    private final MatchMapper matchMapper;

    public void createMatch(MatchRequest matchRequest, UUID homeTeamId, UUID awayTeamId) {
        log.info("Creating match");

        TeamDAO homeTeam = teamManager.findById(homeTeamId)
                .orElseThrow(TeamNotFoundException::new);
        TeamDAO awayTeam = teamManager.findById(awayTeamId)
                .orElseThrow(TeamNotFoundException::new);

        Match match = MatchBuilders.buildFromRequest(matchRequest);
        MatchDAO matchDAO = matchMapper.mapToEntity(match, new CycleAvoidingMappingContext());
        matchDAO.setHomeTeam(homeTeam);
        matchDAO.setAwayTeam(awayTeam);

        matchManager.saveToDatabase(matchDAO);
    }
}
