package com.kotanapp.kotanappapi.modules.article.models;

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
    private String content;
    private String coverImage;
    private Instant publishedAt;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
