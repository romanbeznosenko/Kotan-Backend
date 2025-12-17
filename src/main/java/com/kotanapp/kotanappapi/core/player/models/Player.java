package com.kotanapp.kotanappapi.core.player.models;

import com.kotanapp.kotanappapi.core.team.models.Team;
import com.kotanapp.kotanappapi.utils.enums.PlayerPositionEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    private PlayerId playerId;
    private String firstName;
    private String lastName;
    private PlayerPositionEnum playerPosition;
    private Team team;
    private LocalDate birthDate;
    private String avatar;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
