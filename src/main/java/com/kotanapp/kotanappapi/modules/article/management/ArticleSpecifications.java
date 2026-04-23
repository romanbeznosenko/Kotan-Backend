package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.UUID;

public class ArticleSpecifications {
    public static Specification<ArticleDAO> byCategory(ArticleCategoryEnum category) {
        return ((root, query, criteriaBuilder) -> {
            if (category == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("category"), category);
        });
    }

    public static Specification<ArticleDAO> byId(UUID id) {
        return ((root, query, criteriaBuilder) ->  {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("id"), id);
        });
    }

    public static Specification<ArticleDAO> isArchivedFalse(){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isArchived"), false));
    }
}
