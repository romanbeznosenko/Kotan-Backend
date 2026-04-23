package com.kotanapp.kotanappapi.modules.article.services;

import com.kotanapp.kotanappapi.modules.article.models.Article;
import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import com.kotanapp.kotanappapi.modules.article.models.ArticleListResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
}
