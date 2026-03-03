package com.kotanapp.kotanappapi.modules.playerMatchStatistics.models;

import com.kotanapp.kotanappapi.modules.match.models.Match;
import com.kotanapp.kotanappapi.modules.player.models.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerMatchStatistics {
    private PlayerMatchStatisticsId playerMatchStatisticsId;
    private Match match;
    private Player player;
    private Integer yellowCards;
    private Integer redCards;
    private Integer goals;
    private Integer assists;
    private Boolean cleanSheet;
    private Integer goalsConcede;
    private Integer penaltySaved;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
