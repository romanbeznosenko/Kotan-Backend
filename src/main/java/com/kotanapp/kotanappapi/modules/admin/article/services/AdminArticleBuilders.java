package com.kotanapp.kotanappapi.modules.admin.article.services;

import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleBodyRequest;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleListResponse;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleRequest;
import com.kotanapp.kotanappapi.modules.article.models.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminArticleBuilders {
    public static ArticleBody buildArticleBody(
            AdminArticleBodyRequest request,
            Integer orderIndex,
            Article article
    ) {
        return ArticleBody.builder()
                .articleBodyId(ArticleBodyId.of(null))
                .article(article)
                .type(request.type())
                .text(request.text())
                .orderIndex(orderIndex)
                .isArchived(false)
                .build();
    }

    public static Article buildFromRequest(AdminArticleRequest request) {
        return Article.builder()
                .articleId(ArticleId.of(null))
                .title(request.title())
                .shortPreview(request.shortPreview())
                .category(request.category())
                .image(null)
                .shortPreview(null)
                .isPublished(false)
                .isArchived(false)
                .build();
    }

    public static AdminArticleListResponse buildListResponse(ArticleDAO articleDAO, String image) {
        return AdminArticleListResponse.builder()
                .articleId(articleDAO.getId())
                .title(articleDAO.getTitle())
                .shortPreview(articleDAO.getShortPreview())
                .category(articleDAO.getCategory())
                .date(articleDAO.getCreatedAt())
                .image(image)
                .build();
    }
}
