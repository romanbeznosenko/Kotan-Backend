package com.kotanapp.kotanappapi.core.club.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Club {
    private ClubId clubId;
    private String name;
    private String shortName;
    private String city;
    private String country;
    private String logo;
    private Boolean isOurClub;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
