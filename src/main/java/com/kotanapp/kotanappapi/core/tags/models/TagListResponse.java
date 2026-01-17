package com.kotanapp.kotanappapi.core.tags.models;

import lombok.Builder;

import java.util.List;

@Builder
public record TagListResponse(
        long count,
        List<TagResponse> data
) {
}
