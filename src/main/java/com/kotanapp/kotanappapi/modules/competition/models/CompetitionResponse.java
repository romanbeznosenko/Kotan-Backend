package com.kotanapp.kotanappapi.modules.competition.models;

import com.kotanapp.kotanappapi.utils.enums.CompetitionTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CompetitionResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Competition's name", example = "IV Liga Kobiet")
        String name,

        @Schema(description = "Competition's season", example = "2025/2026")
        String season,

        @Schema(description = "Competition's type", example = "LEAGUE")
        CompetitionTypeEnum type
) {
}
