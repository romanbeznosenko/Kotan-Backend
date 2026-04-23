package com.kotanapp.kotanappapi.modules.article.models;

import com.kotanapp.kotanappapi.utils.enums.ArticleBodyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleBody {
    private ArticleBodyId articleBodyId;
    private Article article;
    private ArticleBodyTypeEnum type;
    private String text;
    private Integer orderIndex;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
