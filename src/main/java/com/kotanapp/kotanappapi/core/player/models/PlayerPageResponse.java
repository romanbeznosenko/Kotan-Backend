package com.kotanapp.kotanappapi.core.player.models;

import lombok.Builder;

import java.util.List;

@Builder
public record PlayerPageResponse(
        long count,
        List<PlayerListResponse> data
) {
}
