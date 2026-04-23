package com.kotanapp.kotanappapi.modules.admin.article.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleBodyRequest;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleRequest;
import com.kotanapp.kotanappapi.modules.article.management.ArticleBodyManager;
import com.kotanapp.kotanappapi.modules.article.management.ArticleBodyMapper;
import com.kotanapp.kotanappapi.modules.article.management.ArticleManager;
import com.kotanapp.kotanappapi.modules.article.management.ArticleMapper;
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
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminCreateArticleService {
    private final ArticleBodyManager articleBodyManager;
    private final ArticleBodyMapper articleBodyMapper;
    private final ArticleManager articleManager;
    private final ArticleMapper articleMapper;
    private final StorageService storageService;

    private final static String IMAGE_FOLDER_NAME = "article_image";
    private final static String HERO_IMAGE_FOLDER_NAME = "article_hero_image";

    public UUID createArticle(AdminArticleRequest request, MultipartFile image, MultipartFile heroImage) throws IOException {
        log.info("Creating new article...");

        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();

        Article article = buildAndSaveArticle(request, image, heroImage, context);

        saveArticleBodies(request, article, context);

        return article.getArticleId().getId();
    }

    private Article buildAndSaveArticle(
            AdminArticleRequest request,
            MultipartFile image,
            MultipartFile heroImage,
            CycleAvoidingMappingContext context
    ) throws IOException {
        Article article = AdminArticleBuilders.buildFromRequest(request);
        if (image != null) {
            String storageKey = storageService.generateStorageKey(UUID.randomUUID(), image, IMAGE_FOLDER_NAME);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(image.getBytes());

            storageService.uploadFile(storageKey, image.getContentType(), outputStream);
            article.setImage(storageKey);
        }

        if (heroImage != null) {
            String storageKey = storageService.generateStorageKey(UUID.randomUUID(), heroImage, HERO_IMAGE_FOLDER_NAME);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(heroImage.getBytes());

            storageService.uploadFile(storageKey, heroImage.getContentType(), outputStream);
            article.setHeroImage(storageKey);
        }

        ArticleDAO articleDAO = articleMapper.mapToEntity(article, context);

        ArticleDAO savedArticle = articleManager.saveToDatabase(articleDAO);

        return articleMapper.mapToDomain(savedArticle, context);
    }

    private void saveArticleBodies(
            AdminArticleRequest request,
            Article article,
            CycleAvoidingMappingContext context
    ) {
        int orderIndex = 1;
        for (AdminArticleBodyRequest bodyRequest : request.body()) {
            ArticleBody articleBody = AdminArticleBuilders.buildArticleBody(
                    bodyRequest,
                    orderIndex++,
                    article
            );

            ArticleBodyDAO articleBodyDAO = articleBodyMapper.mapToEntity(
                    articleBody,
                    context
            );

            articleBodyManager.saveToDatabase(articleBodyDAO);
        }
    }
}