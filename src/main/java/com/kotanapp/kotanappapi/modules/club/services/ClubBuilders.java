package com.kotanapp.kotanappapi.modules.club.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.club.models.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClubBuilders {
    public static Club buildFromRequest(ClubRequest request) {
        return Club.builder()
                .clubId(ClubId.of(null))
                .name(request.name())
                .shortName(request.shortName())
                .city(request.city())
                .country(request.country())
                .isOurClub(request.isOurClub())
                .isArchived(false)
                .build();
    }

    public static ClubListResponse buildListResponse(ClubDAO clubDAO, StorageService storageService) {
        return ClubListResponse.builder()
                .id(clubDAO.getId())
                .name(clubDAO.getName())
                .logo(storageService.createPresignedGetUrl(clubDAO.getLogo()))
                .build();
    }

    public static ClubResponse buildResponse(ClubDAO clubDAO, StorageService storageService) {
        return ClubResponse.builder()
                .id(clubDAO.getId())
                .name(clubDAO.getName())
                .shortName(clubDAO.getShortName())
                .city(clubDAO.getCity())
                .country(clubDAO.getCountry())
                .logo(storageService.createPresignedGetUrl(clubDAO.getLogo()))
                .isOurClub(clubDAO.getIsOurClub())
                .build();
    }
}
