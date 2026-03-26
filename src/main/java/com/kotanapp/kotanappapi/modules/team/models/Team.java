package com.kotanapp.kotanappapi.modules.team.models;

import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    private TeamId teamId;
    private Club club;
    private String name;
    private AgeGroupEnum ageGroup;
    private GenderEnum gender;
    private String coachName;
    private String coverImage;
    private String description;
    private String leagueName;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
