package com.kotanapp.kotanappapi.modules.competition.services;

import com.kotanapp.kotanappapi.modules.competition.management.CompetitionManager;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionPageResponse;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionResponse;
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
public class CompetitionPageService {
    private final CompetitionManager competitionManager;

    public CompetitionPageResponse pageCompetitions(){
        log.info("Paging competitions");

        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);
        Page<CompetitionDAO> competitionDAOPage = competitionManager.findAll(pageable);
        List<CompetitionResponse> competitionResponseList = competitionDAOPage.get()
                .map(CompetitionBuilders::buildResponse)
                .toList();

        return CompetitionPageResponse.builder()
                .count(competitionDAOPage.getTotalElements())
                .data(competitionResponseList)
                .build();
    }
}
