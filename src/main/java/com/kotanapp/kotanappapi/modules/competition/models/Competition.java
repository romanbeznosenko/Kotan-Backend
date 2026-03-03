package com.kotanapp.kotanappapi.modules.competition.models;

import com.kotanapp.kotanappapi.utils.enums.CompetitionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Competition {
    private CompetitionId competitionId;
    private String name;
    private String season;
    private CompetitionType type;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
