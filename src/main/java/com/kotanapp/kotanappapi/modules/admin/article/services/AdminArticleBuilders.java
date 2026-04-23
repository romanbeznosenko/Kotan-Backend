package com.kotanapp.kotanappapi.modules.admin.article.services;

import com.kotanapp.kotanappapi.modules.admin.article.models.*;
import com.kotanapp.kotanappapi.modules.article.models.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public static AdminArticleBodyResponse buildBodyResponse(ArticleBodyDAO articleBodyDAO) {
        return AdminArticleBodyResponse.builder()
                .articleBodyId(articleBodyDAO.getId())
                .type(articleBodyDAO.getType())
                .text(articleBodyDAO.getText())
                .orderIndex(articleBodyDAO.getOrderIndex())
                .build();
    }

    public static AdminArticleResponse buildResponse(ArticleDAO articleDAO, List<AdminArticleBodyResponse> body, String image, String heroImage) {
        return AdminArticleResponse.builder()
                .articleId(articleDAO.getId())
                .title(articleDAO.getTitle())
                .shortPreview(articleDAO.getShortPreview())
                .image(image)
                .heroImage(heroImage)
                .date(articleDAO.getCreatedAt())
                .body(body)
                .build();
    }
}
