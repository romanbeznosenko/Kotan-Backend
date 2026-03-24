package com.kotanapp.kotanappapi.modules.admin.club.services;

import com.kotanapp.kotanappapi.modules.admin.club.models.ClubAdminRequest;
import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClubAdminBuilders {
    public static Club buildClub(ClubAdminRequest request){
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
