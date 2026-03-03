package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.modules.team.models.TeamId;
import com.kotanapp.kotanappapi.modules.team.models.TeamRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamBuilders {
    public static Team buildFromRequest(TeamRequest request, Club club) {
        return Team.builder()
                .teamId(TeamId.of(null))
                .club(club)
                .name(request.name())
                .ageGroup(request.ageGroup())
                .gender(request.gender())
                .coachName(request.coachName())
                .isArchived(false)
                .build();
    }
}
