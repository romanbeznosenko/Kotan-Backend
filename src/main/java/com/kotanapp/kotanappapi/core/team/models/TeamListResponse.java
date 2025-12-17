package com.kotanapp.kotanappapi.core.team.models;

import com.kotanapp.kotanappapi.utils.enums.TeamTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
public record TeamListResponse(
        @Schema(description = "Assigned id", example = "1")
        UUID id,

        @Schema(description = "Team's name", example = "Trampkarz")
        String name,

        @Schema(description = "Team's type", example = "JUNIOR_M")
        TeamTypeEnum teamType
) {
}
