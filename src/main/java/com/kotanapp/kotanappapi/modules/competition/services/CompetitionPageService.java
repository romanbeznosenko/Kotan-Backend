package com.kotanapp.kotanappapi.modules.competition.services;

import com.kotanapp.kotanappapi.modules.competition.management.CompetitionManager;
import com.kotanapp.kotanappapi.modules.competition.management.CompetitionSpecifications;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionPageResponse;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionResponse;
import com.kotanapp.kotanappapi.utils.enums.CompetitionTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompetitionPageService {
    private final CompetitionManager competitionManager;

    public CompetitionPageResponse pageCompetitions(int page, int limit, String name, String season, List<CompetitionTypeEnum> typeList) {
        log.info("Paging competitions");

        Pageable pageable = PageRequest.of(page - 1, limit);
        Specification<CompetitionDAO> spec = CompetitionSpecifications.byName(name)
                .and(CompetitionSpecifications.bySeason(season))
                .and(CompetitionSpecifications.byCompetitionType(typeList))
                .and(CompetitionSpecifications.notIsArchived());
        Page<CompetitionDAO> competitionDAOPage = competitionManager.findAll(spec, pageable);
        List<CompetitionResponse> competitionResponseList = competitionDAOPage.get()
                .map(CompetitionBuilders::buildResponse)
                .toList();

        return CompetitionPageResponse.builder()
                .count(competitionDAOPage.getTotalElements())
                .data(competitionResponseList)
                .build();
    }
}
