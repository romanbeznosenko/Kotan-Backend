package com.kotanapp.kotanappapi.core.team.services;

import com.kotanapp.kotanappapi.core.team.models.*;
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

    public static TeamListResponse buildListResponse(TeamDAO teamDAO){
        return TeamListResponse.builder()
                .id(teamDAO.getId())
                .name(teamDAO.getName())
                .teamType(teamDAO.getTeamType())
                .build();
    }
}
