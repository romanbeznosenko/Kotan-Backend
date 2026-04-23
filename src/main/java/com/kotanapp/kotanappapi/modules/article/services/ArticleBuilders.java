package com.kotanapp.kotanappapi.modules.article.services;

import com.kotanapp.kotanappapi.modules.article.models.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleBuilders {
    public static ArticleListResponse buildListResponse(ArticleDAO articleDAO, String image){
        return ArticleListResponse.builder()
                .articleId(articleDAO.getId())
                .title(articleDAO.getTitle())
                .shortPreview(articleDAO.getShortPreview())
                .category(articleDAO.getCategory())
                .date(articleDAO.getCreatedAt())
                .image(image)
                .build();
    }

    public static ArticleBodyResponse buildArticleBodyResponse(ArticleBodyDAO articleBodyDAO){
        return ArticleBodyResponse.builder()
                .type(articleBodyDAO.getType())
                .text(articleBodyDAO.getText())
                .orderIndex(articleBodyDAO.getOrderIndex())
                .build();
    }

    public static ArticleResponse buildResponse(ArticleDAO articleDAO, String image, String heroImage, List<ArticleBodyResponse> body){
        return ArticleResponse.builder()
                .articleId(articleDAO.getId())
                .title(articleDAO.getTitle())
                .shortPreview(articleDAO.getShortPreview())
                .category(articleDAO.getCategory())
                .date(articleDAO.getCreatedAt())
                .image(image)
                .heroImage(heroImage)
                .body(body)
                .build();
    }
}
