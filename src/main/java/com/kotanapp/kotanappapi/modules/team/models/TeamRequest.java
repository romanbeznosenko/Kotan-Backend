package com.kotanapp.kotanappapi.modules.team.models;

import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TeamRequest(
        @NotBlank
        @Schema(description = "Team's name", example = "Kotan Ozorków")
        String name,

        @NotNull
        @Schema(description = "Team's age group", example = "U7_U8")
        AgeGroupEnum ageGroup,

        @NotNull
        @Schema(description = "Team's gender", example = "MEN")
        GenderEnum gender,

        @Schema(description = "Team's coach name", example = "Mateusz Maciejewski")
        String coachName
) {
}
