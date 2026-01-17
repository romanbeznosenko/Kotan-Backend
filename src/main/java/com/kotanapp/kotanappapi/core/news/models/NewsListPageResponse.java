package com.kotanapp.kotanappapi.core.news.models;

import lombok.Builder;

import java.util.List;

@Builder
public record NewsListPageResponse(
        Long count,
        List<NewsListResponse> data
) {
}
