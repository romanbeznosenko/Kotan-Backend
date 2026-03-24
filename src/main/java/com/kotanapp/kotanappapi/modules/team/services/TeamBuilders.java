package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamListResponse;
import com.kotanapp.kotanappapi.modules.team.models.TeamResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamBuilders {
    public static TeamResponse buildResponse(TeamDAO teamDAO, StorageService storageService) {
        return TeamResponse.builder()
                .id(teamDAO.getId())
                .name(teamDAO.getName())
                .ageGroup(teamDAO.getAgeGroup())
                .gender(teamDAO.getGender())
                .coverImage(storageService.createPresignedGetUrl(teamDAO.getCoverImage()))
                .leagueName(teamDAO.getLeagueName())
                .coachName(teamDAO.getCoachName())
                .description(teamDAO.getDescription())
                .build();
    }

    public static TeamListResponse buildListResponse(TeamDAO teamDAO, StorageService storageService) {
        return TeamListResponse.builder()
                .id(teamDAO.getId())
                .name(teamDAO.getName())
                .ageGroup(teamDAO.getAgeGroup())
                .gender(teamDAO.getGender())
                .coverImage(storageService.createPresignedGetUrl(teamDAO.getCoverImage()))
                .leagueName(teamDAO.getLeagueName())
                .description(teamDAO.getDescription())
                .build();
    }
}
