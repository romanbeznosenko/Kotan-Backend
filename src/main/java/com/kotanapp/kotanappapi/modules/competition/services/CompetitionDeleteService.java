package com.kotanapp.kotanappapi.modules.competition.services;

import com.kotanapp.kotanappapi.modules.competition.management.CompetitionManager;
import com.kotanapp.kotanappapi.modules.competition.management.CompetitionMapper;
import com.kotanapp.kotanappapi.modules.competition.management.CompetitionNotFoundException;
import com.kotanapp.kotanappapi.modules.competition.models.Competition;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompetitionDeleteService {
    private final CompetitionMapper competitionMapper;
    private final CompetitionManager competitionManager;

    public void deleteCompetition(UUID competitionId) {
        log.info("Deleting competition with id: {}", competitionId);

        CompetitionDAO competitionDAO = competitionManager.findById(competitionId)
                .orElseThrow(CompetitionNotFoundException::new);
        Competition competition = competitionMapper.mapToDomain(competitionDAO, new CycleAvoidingMappingContext());

        competition.setIsArchived(true);
        competition.setArchivedAt(Instant.now());

        competitionDAO = competitionMapper.mapToEntity(competition, new CycleAvoidingMappingContext());
        competitionManager.saveToDatabase(competitionDAO);
    }
}
