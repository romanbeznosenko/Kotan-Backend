package com.kotanapp.kotanappapi.modules.competition.models;

import lombok.Builder;

import java.util.List;

@Builder
public record CompetitionPageResponse(
        long count,
        List<CompetitionResponse> data
) {
}
