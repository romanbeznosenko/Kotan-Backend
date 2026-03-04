package com.kotanapp.kotanappapi.modules.competition.services;

import com.kotanapp.kotanappapi.modules.competition.management.CompetitionManager;
import com.kotanapp.kotanappapi.modules.competition.management.CompetitionMapper;
import com.kotanapp.kotanappapi.modules.competition.management.CompetitionNotFoundException;
import com.kotanapp.kotanappapi.modules.competition.models.Competition;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionId;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionRequest;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompetitionEditService {
    private final CompetitionMapper competitionMapper;
    private final CompetitionManager competitionManager;

    public void editCompetition(UUID id, CompetitionRequest request) {
        log.info("Editing competition with id: {}", id);

        CompetitionDAO competitionDAO = competitionManager.findById(id)
                .orElseThrow(CompetitionNotFoundException::new);
        Competition competition = competitionMapper.mapToDomain(competitionDAO, new CycleAvoidingMappingContext());

        competition.setName(request.name());
        competition.setSeason(request.season());
        competition.setType(request.competitionType());

        competitionDAO = competitionMapper.mapToEntity(competition, new CycleAvoidingMappingContext());
        competitionManager.saveToDatabase(competitionDAO);
    }
}
