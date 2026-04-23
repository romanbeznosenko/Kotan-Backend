package com.kotanapp.kotanappapi.modules.admin.article.models;

import com.kotanapp.kotanappapi.utils.enums.ArticleBodyTypeEnum;
import lombok.Builder;

import java.util.UUID;

@Builder
public record AdminArticleBodyResponse(
        UUID articleBodyId,
        ArticleBodyTypeEnum type,
        String text,
        Integer orderIndex
) {
}
