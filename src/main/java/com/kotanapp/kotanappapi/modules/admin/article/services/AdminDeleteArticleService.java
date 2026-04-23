package com.kotanapp.kotanappapi.modules.admin.article.services;

import com.kotanapp.kotanappapi.modules.article.management.*;
import com.kotanapp.kotanappapi.modules.article.models.Article;
import com.kotanapp.kotanappapi.modules.article.models.ArticleBody;
import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminDeleteArticleService {
    private final ArticleManager articleManager;
    private final ArticleMapper articleMapper;
    private final ArticleBodyManager articleBodyManager;
    private final ArticleBodyMapper articleBodyMapper;

    public void deleteArticle(UUID articleId) {
        log.info("Deleting article with id: {}", articleId);

        ArticleDAO articleDAO = articleManager.findOne(
                ArticleSpecifications.byId(articleId)
                        .and(ArticleSpecifications.isArchivedFalse())
        ).orElseThrow(ArticleNotFoundException::new);

        List<ArticleBodyDAO> articleBodyDAOList = articleBodyManager.findAll(
                ArticleBodySpecifications.byArticle(articleDAO)
                        .and(ArticleBodySpecifications.isArchivedFalse())
        );

        articleBodyDAOList.forEach(articleBodyDAO -> {
            ArticleBody articleBody = articleBodyMapper.mapToDomain(articleBodyDAO, new CycleAvoidingMappingContext());
            articleBody.setIsArchived(true);
            articleBody.setArchivedAt(Instant.now());
            articleBodyDAO = articleBodyMapper.mapToEntity(articleBody, new CycleAvoidingMappingContext());

            articleBodyManager.saveToDatabase(articleBodyDAO);
        });

        Article article = articleMapper.mapToDomain(articleDAO, new CycleAvoidingMappingContext());
        article.setIsArchived(true);
        article.setArchivedAt(Instant.now());
        articleDAO = articleMapper.mapToEntity(article, new CycleAvoidingMappingContext());
        articleManager.saveToDatabase(articleDAO);
    }
}
