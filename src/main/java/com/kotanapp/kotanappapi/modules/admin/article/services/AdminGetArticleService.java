package com.kotanapp.kotanappapi.modules.admin.article.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleBodyResponse;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleResponse;
import com.kotanapp.kotanappapi.modules.article.management.*;
import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminGetArticleService {
    private final ArticleManager articleManager;
    private final ArticleBodyManager articleBodyManager;
    private final StorageService storageService;

    public AdminArticleResponse getArticle(UUID articleId) {
        log.info("Getting article with id: {}", articleId);

        ArticleDAO articleDAO = articleManager.findOne(
                ArticleSpecifications.byId(articleId)
                        .and(ArticleSpecifications.isArchivedFalse())
        ).orElseThrow(ArticleNotFoundException::new);

        List<ArticleBodyDAO> articleBodyDAOList = articleBodyManager.findAll(
                ArticleBodySpecifications.byArticle(articleDAO)
                        .and(ArticleBodySpecifications.isArchivedFalse())
        );

        List<AdminArticleBodyResponse> body = articleBodyDAOList.stream()
                .map(AdminArticleBuilders::buildBodyResponse)
                .toList();

        String image = storageService.createPresignedGetUrl(articleDAO.getImage());
        String heroImage = storageService.createPresignedGetUrl(articleDAO.getHeroImage());

        return AdminArticleBuilders.buildResponse(articleDAO, body, image, heroImage);
    }
}
