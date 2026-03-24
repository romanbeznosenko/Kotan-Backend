package com.kotanapp.kotanappapi.modules.admin.team.models;

import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record TeamAdminListResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Team's name", example = "Seniorzy")
        String name,

        @Schema(description = "Team's age group", example = "U7_U8")
        AgeGroupEnum ageGroup,

        @Schema(description = "Team's gender", example = "MEN")
        GenderEnum gender,

        @Schema(description = "Team's coach name", example = "Ernesto Valverde")
        String coachName,

        @Schema(description = "Team's photo", example = "https://example.com/example.png")
        String photo
) {
}
