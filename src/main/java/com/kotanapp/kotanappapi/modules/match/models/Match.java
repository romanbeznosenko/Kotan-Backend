package com.kotanapp.kotanappapi.modules.match.models;

import com.kotanapp.kotanappapi.modules.competition.models.Competition;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.utils.enums.MatchStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private MatchId matchId;
    private Team homeTeam;
    private Team awayTeam;
    private Competition competition;
    private LocalDateTime matchDate;
    private String stadium;
    private Integer homeScore;
    private Integer awayScore;
    private MatchStatusEnum status;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
