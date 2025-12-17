package com.kotanapp.kotanappapi.core.match.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record MatchRequest(
        @Schema(description = "Match's start time", example = "2025-12-17 10:48:55.980997+01")
        @NotNull
        Instant startTime,

        @Schema(description = "Match's location", example = "Ozorków, ul. Leśna 1")
        @NotNull
        String location
) {
}
