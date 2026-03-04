package com.kotanapp.kotanappapi.modules.competition.services;

import com.kotanapp.kotanappapi.modules.competition.models.Competition;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionId;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompetitionBuilders {
    public static Competition buildFromRequest(CompetitionRequest request) {
        return Competition.builder()
                .competitionId(CompetitionId.of(null))
                .name(request.name())
                .season(request.season())
                .type(request.competitionType())
                .isArchived(false)
                .build();
    }
}
