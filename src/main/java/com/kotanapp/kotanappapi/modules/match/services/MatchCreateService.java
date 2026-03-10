package com.kotanapp.kotanappapi.modules.match.services;

import com.kotanapp.kotanappapi.modules.competition.management.CompetitionManager;
import com.kotanapp.kotanappapi.modules.competition.management.CompetitionMapper;
import com.kotanapp.kotanappapi.modules.competition.management.CompetitionNotFoundException;
import com.kotanapp.kotanappapi.modules.competition.models.Competition;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.modules.match.management.MatchManager;
import com.kotanapp.kotanappapi.modules.match.management.MatchMapper;
import com.kotanapp.kotanappapi.modules.match.models.Match;
import com.kotanapp.kotanappapi.modules.match.models.MatchDAO;
import com.kotanapp.kotanappapi.modules.match.models.MatchRequest;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamMapper;
import com.kotanapp.kotanappapi.modules.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchCreateService {
    private final TeamManager teamManager;
    private final TeamMapper teamMapper;
    private final CompetitionManager competitionManager;
    private final CompetitionMapper competitionMapper;
    private final MatchManager matchManager;
    private final MatchMapper matchMapper;

    public void createMatch(MatchRequest request){
        log.info("Creating new match");

        TeamDAO homeTeamDAO = teamManager.findById(request.homeTeamId())
                .orElseThrow(TeamNotFoundException::new);
        Team homeTeam = teamMapper.mapToDomain(homeTeamDAO, new CycleAvoidingMappingContext());

        TeamDAO awayTeamDAO = teamManager.findById(request.awayTeamId())
                .orElseThrow(TeamNotFoundException::new);
        Team awayTeam = teamMapper.mapToDomain(awayTeamDAO, new CycleAvoidingMappingContext());

        CompetitionDAO competitionDAO = competitionManager.findById(request.competitionId())
                .orElseThrow(CompetitionNotFoundException::new);
        Competition competition = competitionMapper.mapToDomain(competitionDAO, new CycleAvoidingMappingContext());

        Match match = MatchBuilders.buildFromRequest(request, homeTeam, awayTeam, competition);
        MatchDAO matchDAO = matchMapper.mapToEntity(match, new CycleAvoidingMappingContext());
        matchManager.saveToDatabase(matchDAO);
    }
}
