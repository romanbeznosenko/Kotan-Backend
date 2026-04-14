package com.kotanapp.kotanappapi.modules.admin.article.models;

import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AdminArticleRequest(
        @NotBlank
        String title,

        @NotBlank
        String shortPreview,

        @NotNull
        ArticleCategoryEnum category,

        @NotNull
        List<AdminArticleBodyRequest> body
) {
}
