package com.kotanapp.kotanappapi.core.team.services;

import com.kotanapp.kotanappapi.core.team.models.Team;
import com.kotanapp.kotanappapi.core.team.models.TeamId;
import com.kotanapp.kotanappapi.core.team.models.TeamRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamBuilders {
    public static Team buildFromRequest(TeamRequest request){
        return Team.builder()
                .teamId(TeamId.of(null))
                .name(request.name())
                .logo(null)
                .teamType(request.teamType())
                .isArchived(false)
                .build();
    }
}
