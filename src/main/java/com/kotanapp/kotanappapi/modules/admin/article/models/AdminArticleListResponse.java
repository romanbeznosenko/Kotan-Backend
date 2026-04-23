package com.kotanapp.kotanappapi.modules.admin.article.models;

import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record AdminArticleListResponse(
        UUID articleId,
        String title,
        String shortPreview,
        ArticleCategoryEnum category,
        Instant date,
        String image
) {
}
