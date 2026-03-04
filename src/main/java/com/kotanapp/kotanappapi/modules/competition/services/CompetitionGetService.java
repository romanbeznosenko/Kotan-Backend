package com.kotanapp.kotanappapi.modules.competition.services;

import com.kotanapp.kotanappapi.modules.competition.management.CompetitionManager;
import com.kotanapp.kotanappapi.modules.competition.management.CompetitionNotFoundException;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompetitionGetService {
    private final CompetitionManager competitionManager;

    public CompetitionResponse get(UUID competitionId) {
        log.info("Getting competition with id: {}", competitionId);

        CompetitionDAO competitionDAO = competitionManager.findById(competitionId)
                .orElseThrow(CompetitionNotFoundException::new);

        return CompetitionBuilders.buildResponse(competitionDAO);
    }
}
