package com.kotanapp.kotanappapi.core.match.services;

import com.kotanapp.kotanappapi.core.match.models.*;
import com.kotanapp.kotanappapi.core.team.models.TeamMatchResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchBuilders {
    public static Match buildFromRequest(MatchRequest matchRequest) {
        return Match.builder()
                .matchId(MatchId.of(null))
                .startTime(matchRequest.startTime())
                .location(matchRequest.location())
                .isFinished(false)
                .isArchived(false)
                .build();
    }

    public static MatchListResponse buildMatchListResponse(MatchDAO matchDAO, TeamMatchResponse homeTeam, TeamMatchResponse awayTeam) {
        return MatchListResponse.builder()
                .id(matchDAO.getId())
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .startTime(matchDAO.getStartTime())
                .location(matchDAO.getLocation())
                .isFinished(matchDAO.getIsFinished())
                .result(matchDAO.getResult())
                .build();
    }
}
