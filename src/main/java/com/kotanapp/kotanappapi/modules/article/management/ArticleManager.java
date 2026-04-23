package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleManager {
    private final ArticleRepository articleRepository;

    public ArticleDAO saveToDatabase(ArticleDAO article) {
        return articleRepository.save(article);
    }

    public Page<ArticleDAO> findAllArticles(Specification<ArticleDAO> specification, Pageable pageable) {
        return articleRepository.findAll(specification, pageable);
    }
}