package com.kotanapp.kotanappapi.modules.admin.article.models;

import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record AdminArticleResponse(
        UUID articleId,
        String title,
        String shortPreview,
        String image,
        String heroImage,
        Instant date,
        List<AdminArticleBodyResponse> body
) {
}
