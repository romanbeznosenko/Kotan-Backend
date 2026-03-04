package com.kotanapp.kotanappapi.modules.competition.services;

import com.kotanapp.kotanappapi.modules.competition.models.*;
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

    public static CompetitionResponse buildResponse(CompetitionDAO competitionDAO) {
        return CompetitionResponse.builder()
                .id(competitionDAO.getId())
                .name(competitionDAO.getName())
                .season(competitionDAO.getSeason())
                .type(competitionDAO.getType())
                .build();
    }
}
