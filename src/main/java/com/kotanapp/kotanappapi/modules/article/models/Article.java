package com.kotanapp.kotanappapi.modules.article.models;

import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private ArticleId articleId;
    private String title;
    private String shortPreview;
    private ArticleCategoryEnum category;
    private String image;
    private String heroImage;
    private Boolean isPublished;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
