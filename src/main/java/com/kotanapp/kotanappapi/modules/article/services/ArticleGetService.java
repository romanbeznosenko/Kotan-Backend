package com.kotanapp.kotanappapi.modules.article.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.article.management.*;
import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyResponse;
import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import com.kotanapp.kotanappapi.modules.article.models.ArticleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticleGetService {
    private final ArticleManager articleManager;
    private final ArticleBodyManager articleBodyManager;
    private final StorageService storageService;

    public ArticleResponse getArticle(UUID articleId) {
        log.info("Getting article with id: {}", articleId);

        ArticleDAO articleDAO = articleManager.findOne(
                ArticleSpecifications.byId(articleId)
                        .and(ArticleSpecifications.isArchivedFalse())
        ).orElseThrow(ArticleNotFoundException::new);

        List<ArticleBodyDAO> articleBodyDAOList = articleBodyManager.findAll(
                ArticleBodySpecifications.byArticle(articleDAO)
                        .and(ArticleBodySpecifications.isArchivedFalse())
        );
        List<ArticleBodyResponse> body = articleBodyDAOList
                .stream()
                .map(ArticleBuilders::buildArticleBodyResponse)
                .toList();

        String image = storageService.createPresignedGetUrl(articleDAO.getImage());
        String heroImage = storageService.createPresignedGetUrl(articleDAO.getHeroImage());

        return ArticleBuilders.buildResponse(articleDAO, image, heroImage, body);
    }
}
