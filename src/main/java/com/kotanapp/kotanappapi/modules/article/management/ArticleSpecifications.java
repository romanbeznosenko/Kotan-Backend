package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import org.springframework.data.jpa.domain.Specification;

public class ArticleSpecifications {
    public static Specification<ArticleDAO> byCategory(ArticleCategoryEnum category) {
        return ((root, query, criteriaBuilder) -> {
            if (category == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("category"), category);
        });
    }
}
