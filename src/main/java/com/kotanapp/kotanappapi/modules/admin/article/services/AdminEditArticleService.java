package com.kotanapp.kotanappapi.modules.admin.article.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleBodyRequest;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleRequest;
import com.kotanapp.kotanappapi.modules.article.management.*;
import com.kotanapp.kotanappapi.modules.article.models.Article;
import com.kotanapp.kotanappapi.modules.article.models.ArticleBody;
import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminEditArticleService {
    private final ArticleManager articleManager;
    private final ArticleMapper articleMapper;
    private final ArticleBodyManager articleBodyManager;
    private final ArticleBodyMapper articleBodyMapper;
    private final StorageService storageService;

    private final static String IMAGE_FOLDER_NAME = "article_image";
    private final static String HERO_IMAGE_FOLDER_NAME = "article_hero_image";

    public UUID editArticle(UUID articleId, AdminArticleRequest request, MultipartFile image, MultipartFile heroImage) throws IOException {
        log.info("Editing article with id: {}", articleId);

        ArticleDAO articleDAO = articleManager.findOne(
                ArticleSpecifications.byId(articleId)
                        .and(ArticleSpecifications.isArchivedFalse())
        ).orElseThrow(ArticleNotFoundException::new);
        Article article = articleMapper.mapToDomain(articleDAO, new CycleAvoidingMappingContext());

        List<ArticleBodyDAO> articleBodyDAOList = articleBodyManager.findAll(
                ArticleBodySpecifications.byArticle(articleDAO)
                        .and(ArticleBodySpecifications.isArchivedFalse())
        );
        articleBodyManager.deleteAll(articleBodyDAOList);

        int orderIndex = 1;
        for (AdminArticleBodyRequest bodyRequest : request.body()) {
            ArticleBody articleBody = AdminArticleBuilders.buildArticleBody(
                    bodyRequest,
                    orderIndex++,
                    article
            );
            ArticleBodyDAO articleBodyDAO = articleBodyMapper.mapToEntity(articleBody, new CycleAvoidingMappingContext());
            articleBodyManager.saveToDatabase(articleBodyDAO);
        }

        article.setTitle(request.title());
        article.setShortPreview(request.shortPreview());
        article.setCategory(request.category());

        if (image != null) {
            String oldStorageKey = article.getImage();

            String storageKey = storageService.generateStorageKey(UUID.randomUUID(), image, IMAGE_FOLDER_NAME);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os.write(image.getBytes());

            storageService.uploadFile(storageKey, image.getContentType(), os);

            if (oldStorageKey != null) {
                storageService.deleteFile(oldStorageKey);
            }

            article.setImage(storageKey);
        }

        if (heroImage != null) {
            String oldStorageKey = article.getHeroImage();

            String storageKey = storageService.generateStorageKey(UUID.randomUUID(), heroImage, HERO_IMAGE_FOLDER_NAME);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            os.write(heroImage.getBytes());

            storageService.uploadFile(storageKey, heroImage.getContentType(), os);

            if (oldStorageKey != null) {
                storageService.deleteFile(oldStorageKey);
            }

            article.setHeroImage(storageKey);
        }

        articleDAO = articleMapper.mapToEntity(article, new CycleAvoidingMappingContext());
        articleDAO = articleManager.saveToDatabase(articleDAO);

        return articleDAO.getId();
    }
}
