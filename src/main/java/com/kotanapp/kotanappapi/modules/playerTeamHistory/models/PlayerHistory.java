package com.kotanapp.kotanappapi.modules.playerTeamHistory.models;

import com.kotanapp.kotanappapi.modules.player.models.Player;
import com.kotanapp.kotanappapi.modules.team.models.Team;
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
public class PlayerHistory {
    private PlayerHistoryId playerHistoryId;
    private Player player;
    private Team team;
    private LocalDate fromDate;
    private LocalDate toDate;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
