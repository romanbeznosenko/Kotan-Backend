package com.kotanapp.kotanappapi.modules.competition.models;

import com.kotanapp.kotanappapi.utils.enums.CompetitionTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CompetitionRequest(
        @NotBlank
        @Schema(description = "Competition's name", example = "IV Liga Kobiet")
        String name,

        @NotBlank
        @Schema(description = "Competition's season", example = "2025/2026")
        String season,

        @NotNull
        @Schema(description = "Competition's type", example = "LEAGUE")
        CompetitionTypeEnum competitionType
) {
}
