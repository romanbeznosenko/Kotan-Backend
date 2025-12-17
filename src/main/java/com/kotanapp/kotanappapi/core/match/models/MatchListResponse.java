package com.kotanapp.kotanappapi.core.match.models;

import com.kotanapp.kotanappapi.core.team.models.TeamMatchResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record MatchListResponse(
        @Schema(description = "Assigned ID", example = "1")
        UUID id,

        @Schema(description = "Home team", implementation = TeamMatchResponse.class)
        TeamMatchResponse homeTeam,

        @Schema(description = "Away team", implementation = TeamMatchResponse.class)
        TeamMatchResponse awayTeam,

        @Schema(description = "Match's start time", example = "2025-12-17 10:48:55.980997+01")
        Instant startTime,

        @Schema(description = "Match's location", example = "Ozorków, ul. Leśna 1")
        String location
) {
}
