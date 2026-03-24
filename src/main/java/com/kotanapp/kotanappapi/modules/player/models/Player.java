package com.kotanapp.kotanappapi.modules.player.models;

import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.endpoints.internal.Value;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private PlayerId playerId;
    private Team team;
    private String firstName;
    private String lastName;
    private PositionEnum position;
    private String jerseyNumber;
    private LocalDate dateOfBirth;
    private String photo;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
