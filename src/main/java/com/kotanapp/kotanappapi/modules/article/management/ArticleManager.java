package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleManager {
    private final ArticleRepository articleRepository;

    public ArticleDAO saveToDatabase(ArticleDAO article) {
        return articleRepository.save(article);
    }
}