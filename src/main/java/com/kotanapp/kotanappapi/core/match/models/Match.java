package com.kotanapp.kotanappapi.core.match.models;

import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.models.Team;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Match {
    private MatchId matchId;
    private Team homeTeam;
    private Team awayTeam;
    private Instant startTime;
    private String location;
    private String result;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Boolean archivedAt;
}
