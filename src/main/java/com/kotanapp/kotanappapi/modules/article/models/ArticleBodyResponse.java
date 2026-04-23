package com.kotanapp.kotanappapi.modules.article.models;

import com.kotanapp.kotanappapi.utils.enums.ArticleBodyTypeEnum;
import lombok.Builder;

@Builder
public record ArticleBodyResponse(
        ArticleBodyTypeEnum type,
        String text,
        Integer orderIndex
) {
}
