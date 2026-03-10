package com.kotanapp.kotanappapi.modules.match.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record MatchRequest(
        @NotNull
        @Schema(description = "Home team's id", example = "1")
        UUID homeTeamId,

        @NotNull
        @Schema(description = "Away team's id", example = "2")
        UUID awayTeamId,

        @NotNull
        @Schema(description = "Competition's id", example = "3")
        UUID competitionId,

        @NotNull
        @Schema(description = "Match datetime")
        LocalDateTime matchDatetime,

        @NotBlank
        @Schema(description = "Match stadium", example = "ul. Leśna 1, Ozorków")
        String stadium
) {
}
