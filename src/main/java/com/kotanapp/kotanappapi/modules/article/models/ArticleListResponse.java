package com.kotanapp.kotanappapi.modules.article.models;

import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record ArticleListResponse(
        UUID articleId,
        String title,
        String shortPreview,
        ArticleCategoryEnum category,
        Instant date,
        String image
) {
}
