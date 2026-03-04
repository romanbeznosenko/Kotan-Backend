package com.kotanapp.kotanappapi.modules.competition.services;

import com.kotanapp.kotanappapi.modules.competition.management.CompetitionManager;
import com.kotanapp.kotanappapi.modules.competition.management.CompetitionMapper;
import com.kotanapp.kotanappapi.modules.competition.models.Competition;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionRequest;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompetitionCreateService {
    private final CompetitionManager competitionManager;
    private final CompetitionMapper competitionMapper;

    public void createCompetition(CompetitionRequest request) {
        log.info("Creating new competition");

        Competition competition = CompetitionBuilders.buildFromRequest(request);
        CompetitionDAO competitionDAO = competitionMapper.mapToEntity(competition, new CycleAvoidingMappingContext());

        competitionManager.saveToDatabase(competitionDAO);
    }
}
