package com.kotanapp.kotanappapi.modules.team.models;

import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record TeamListResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Team's name", example = "Junior")
        String name,

        @Schema(description = "Team's age group", example = "U7_U8")
        AgeGroupEnum ageGroup,

        @Schema(description = "Team's gender", example = "MEN")
        GenderEnum gender,

        @Schema(description = "Team's cover image", example = "https://example.com/example.png")
        String coverImage,

        @Schema(description = "Team's league name", example = "Lodzka A Klasa")
        String leagueName,

        @Schema(description = "Team's description")
        String description
        ) {
}
