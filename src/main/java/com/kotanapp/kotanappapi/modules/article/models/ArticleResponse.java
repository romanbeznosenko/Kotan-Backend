package com.kotanapp.kotanappapi.modules.article.models;

import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Builder
public record ArticleResponse(
        UUID articleId,
        String title,
        String shortPreview,
        ArticleCategoryEnum category,
        Instant date,
        String image,
        String heroImage,
        List<ArticleBodyResponse> body
) {
}
