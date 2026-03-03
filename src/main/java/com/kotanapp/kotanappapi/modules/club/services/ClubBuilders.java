package com.kotanapp.kotanappapi.modules.club.services;

import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubId;
import com.kotanapp.kotanappapi.modules.club.models.ClubRequest;
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
}
