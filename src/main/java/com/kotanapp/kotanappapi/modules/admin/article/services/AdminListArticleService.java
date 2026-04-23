package com.kotanapp.kotanappapi.modules.admin.article.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleListResponse;
import com.kotanapp.kotanappapi.modules.article.management.ArticleManager;
import com.kotanapp.kotanappapi.modules.article.management.ArticleSpecifications;
import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminListArticleService {
    private final ArticleManager articleManager;
    private final StorageService storageService;

    public CustomPaginationResponse<AdminArticleListResponse> listArticles(int page, int limit, ArticleCategoryEnum category) {
        log.info("Listing articles...");

        PageRequest pageRequest = PageRequest.of(page - 1, limit);
        Specification<ArticleDAO> spec = ArticleSpecifications.byCategory(category)
                .and(ArticleSpecifications.isArchivedFalse());

        Page<ArticleDAO> articleDAOPage = articleManager.findAllArticles(spec, pageRequest);
        List<AdminArticleListResponse> listResponse = articleDAOPage.get()
                .map(item -> {
                    String image = storageService.createPresignedGetUrl(item.getImage());

                    return AdminArticleBuilders.buildListResponse(item, image);
                })
                .toList();

        return new CustomPaginationResponse<>(listResponse, articleDAOPage.getTotalElements());
    }
}
