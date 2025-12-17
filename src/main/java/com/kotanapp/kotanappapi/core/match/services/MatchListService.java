package com.kotanapp.kotanappapi.core.match.services;

import com.kotanapp.kotanappapi.core.match.management.MatchManager;
import com.kotanapp.kotanappapi.core.match.models.MatchDAO;
import com.kotanapp.kotanappapi.core.match.models.MatchListResponse;
import com.kotanapp.kotanappapi.core.match.models.MatchPageResponse;
import com.kotanapp.kotanappapi.core.team.models.TeamMatchResponse;
import com.kotanapp.kotanappapi.core.team.services.TeamBuilders;
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
public class MatchListService {
    private final MatchManager matchManager;

    public MatchPageResponse listMatches(int page, int limit) {
        log.info("listMatches page: {}, limit: {}", page, limit);

        Pageable pageable = PageRequest.of(page - 1, limit);
        Page<MatchDAO> matchDAOPage = matchManager.findAll(pageable);
        List<MatchListResponse> data = matchDAOPage.get()
                .map(item -> {
                    TeamMatchResponse homeTeam = TeamBuilders.buildMatchResponse(item.getHomeTeam());
                    TeamMatchResponse awayTeam = TeamBuilders.buildMatchResponse(item.getAwayTeam());
                    return MatchBuilders.buildMatchListResponse(item, homeTeam, awayTeam);
                })
                .toList();

        return MatchPageResponse.builder()
                .data(data)
                .count(matchDAOPage.getTotalElements())
                .build();
    }
}
