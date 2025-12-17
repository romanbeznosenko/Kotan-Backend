package com.kotanapp.kotanappapi.core.match.models;

import lombok.Builder;

import java.util.List;

@Builder
public record MatchPageResponse(
        long count,
        List<MatchListResponse> data
) {
}
