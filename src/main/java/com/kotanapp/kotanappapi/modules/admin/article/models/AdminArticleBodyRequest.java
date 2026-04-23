package com.kotanapp.kotanappapi.modules.admin.article.models;

import com.kotanapp.kotanappapi.utils.enums.ArticleBodyTypeEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AdminArticleBodyRequest(
        @NotNull
        ArticleBodyTypeEnum type,

        @NotBlank
        String text
) {
}
