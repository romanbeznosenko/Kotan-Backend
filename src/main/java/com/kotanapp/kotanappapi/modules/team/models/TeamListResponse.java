package com.kotanapp.kotanappapi.modules.team.models;

import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record TeamListResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Team's name", example = "Kotan Ozorków")
        String name,

        @Schema(description = "Team's age group", example = "U7_U8")
        AgeGroupEnum ageGroup,

        @Schema(description = "Team's gender", example = "MEN")
        GenderEnum gender
) {
}
