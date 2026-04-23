package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import org.springframework.data.jpa.domain.Specification;

public class ArticleBodySpecifications {
    public static Specification<ArticleBodyDAO> byArticle(ArticleDAO article) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (article == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("article"), article);
        };
    }

    public static Specification<ArticleBodyDAO> isArchivedFalse(){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isArchived"), false));
    }
}
