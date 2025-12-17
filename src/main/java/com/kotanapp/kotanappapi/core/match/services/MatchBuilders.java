package com.kotanapp.kotanappapi.core.match.services;

import com.kotanapp.kotanappapi.core.match.models.Match;
import com.kotanapp.kotanappapi.core.match.models.MatchId;
import com.kotanapp.kotanappapi.core.match.models.MatchRequest;
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
}
