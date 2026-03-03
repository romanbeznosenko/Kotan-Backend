package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.team.models.*;
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

    public static TeamListResponse buildListResponse(TeamDAO teamDAO){
        return TeamListResponse.builder()
                .id(teamDAO.getId())
                .name(teamDAO.getName())
                .ageGroup(teamDAO.getAgeGroup())
                .gender(teamDAO.getGender())
                .build();
    }
}
