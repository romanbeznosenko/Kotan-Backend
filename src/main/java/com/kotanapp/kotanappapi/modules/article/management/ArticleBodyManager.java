package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleBodyManager {
    private final ArticleBodyRepository articleBodyRepository;

    public ArticleBodyDAO saveToDatabase(ArticleBodyDAO articleBody) {
        return articleBodyRepository.save(articleBody);
    }

    public List<ArticleBodyDAO> findAll(Specification<ArticleBodyDAO> specification) {
        return articleBodyRepository.findAll(specification);
    }
}