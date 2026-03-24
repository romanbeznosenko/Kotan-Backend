package com.kotanapp.kotanappapi.modules.admin.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.team.models.TeamAdminListResponse;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamAdminBuilders {
    public static TeamAdminListResponse buildListResponse(TeamDAO teamDAO, StorageService storageService) {
        return TeamAdminListResponse.builder()
                .id(teamDAO.getId())
                .name(teamDAO.getName())
                .ageGroup(teamDAO.getAgeGroup())
                .gender(teamDAO.getGender())
                .coachName(teamDAO.getCoachName())
                .photo(storageService.createPresignedGetUrl(teamDAO.getClub().getLogo()))
                .build();
    }
}
