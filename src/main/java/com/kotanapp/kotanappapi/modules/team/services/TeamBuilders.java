package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.team.models.*;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
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

    public static TeamResponse buildResponse(TeamDAO teamDAO, StorageService storageService) {
        return TeamResponse.builder()
                .id(teamDAO.getId())
                .name(teamDAO.getName())
                .ageGroup(teamDAO.getAgeGroup())
                .gender(teamDAO.getGender())
                .coachName(teamDAO.getCoachName())
                .coverImage(storageService.createPresignedGetUrl(teamDAO.getCoverImage()))
                .build();
    }

    public static Team buildFromCSV(String[] parts, Club club) {
        AgeGroupEnum ageGroup = null;
        if (parts[2].trim().equals("U6_U7")) {
            ageGroup = AgeGroupEnum.U6_U7;
        } else if (parts[2].trim().equals("U8_U9")) {
            ageGroup = AgeGroupEnum.U8_U9;
        } else if (parts[2].trim().equals("U10_U11")) {
            ageGroup = AgeGroupEnum.U10_U11;
        } else if (parts[2].trim().equals("U12_U13")) {
            ageGroup = AgeGroupEnum.U12_U13;
        } else if  (parts[2].trim().equals("U14_U15")) {
            ageGroup = AgeGroupEnum.U14_U15;
        } else if (parts[2].trim().equals("U16_U17")) {
            ageGroup = AgeGroupEnum.U16_U17;
        } else if (parts[2].trim().equals("U18_U19")) {
            ageGroup = AgeGroupEnum.U18_U19;
        } else {
            ageGroup = AgeGroupEnum.SENIOR;
        }

        return Team.builder()
                .teamId(TeamId.of(null))
                .club(club)
                .name(parts[1].trim())
                .ageGroup(ageGroup)
                .gender(parts[3].trim().equals("MEN") ? GenderEnum.MEN : GenderEnum.WOMEN)
                .coachName(parts[4].trim())
                .isArchived(false)
                .build();
    }
}
