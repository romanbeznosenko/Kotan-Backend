package com.kotanapp.kotanappapi.modules.team.models;

import lombok.Builder;

import java.util.List;

@Builder
public record TeamPageResponse(
        long count,
        List<TeamListResponse> data
) {
}
