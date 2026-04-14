package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleBodyManager {
    private final ArticleBodyRepository articleBodyRepository;

    public ArticleBodyDAO saveToDatabase(ArticleBodyDAO articleBody) {
        return articleBodyRepository.save(articleBody);
    }
}