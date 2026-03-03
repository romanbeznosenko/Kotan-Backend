package com.kotanapp.kotanappapi.modules.club.models;

import lombok.Builder;

import java.util.List;

@Builder
public record ClubPageResponse(
        long count,
        List<ClubListResponse> data
) {
}
