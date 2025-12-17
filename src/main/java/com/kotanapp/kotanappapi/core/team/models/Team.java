package com.kotanapp.kotanappapi.core.team.models;

import com.kotanapp.kotanappapi.utils.enums.TeamTypeEnum;
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
    private String name;
    private String logo;
    private TeamTypeEnum teamType;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
