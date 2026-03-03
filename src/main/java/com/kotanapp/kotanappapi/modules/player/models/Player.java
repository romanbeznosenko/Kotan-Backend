package com.kotanapp.kotanappapi.modules.player.models;

import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
import com.kotanapp.kotanappapi.utils.enums.PreferredFootEnum;
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
    private Team team;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String nationality;
    private Long height;
    private Long weight;
    private PreferredFootEnum preferredFoot;
    private PositionEnum position;
    private String photo;
    private String shortNumber;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
