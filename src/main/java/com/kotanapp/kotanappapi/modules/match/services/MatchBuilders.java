package com.kotanapp.kotanappapi.modules.match.services;

import com.kotanapp.kotanappapi.modules.competition.models.Competition;
import com.kotanapp.kotanappapi.modules.match.models.Match;
import com.kotanapp.kotanappapi.modules.match.models.MatchId;
import com.kotanapp.kotanappapi.modules.match.models.MatchRequest;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.utils.enums.MatchStatusEnum;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchBuilders {
    public static Match buildFromRequest(MatchRequest request, Team homeTeam, Team awayTeam, Competition competition) {
        return Match.builder()
                .matchId(MatchId.of(null))
                .homeTeam(homeTeam)
                .awayTeam(awayTeam)
                .competition(competition)
                .stadium(request.stadium())
                .matchDate(request.matchDatetime())
                .status(MatchStatusEnum.SCHEDULED)
                .isArchived(false)
                .build();
    }
}
