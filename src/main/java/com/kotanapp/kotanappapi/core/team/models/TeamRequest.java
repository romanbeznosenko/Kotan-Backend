package com.kotanapp.kotanappapi.core.team.models;

import com.kotanapp.kotanappapi.utils.enums.TeamTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TeamRequest (
        @NotBlank
        @Schema(description = "Team's name", example = "Trampkarz")
        String name,

        @NotNull
        @Schema(description = "Teams' type", example = "JUNIOR_M")
        TeamTypeEnum teamType
){
}
