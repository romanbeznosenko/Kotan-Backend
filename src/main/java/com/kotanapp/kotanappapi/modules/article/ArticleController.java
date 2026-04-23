package com.kotanapp.kotanappapi.modules.article;

import com.kotanapp.kotanappapi.modules.article.models.ArticleListResponse;
import com.kotanapp.kotanappapi.modules.article.models.ArticleResponse;
import com.kotanapp.kotanappapi.modules.article.services.ArticleGetService;
import com.kotanapp.kotanappapi.modules.article.services.ArticleListService;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/public/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleListService articleListService;
    private final ArticleGetService articleGetService;

    private static final String DEFAULT_RESPONSE = "Operation successful.";

    @GetMapping(value = "/list")
    @Operation(
            description = "List articles",
            summary = "List articles"
    )
    @PreAuthorize("permitAll()")
    public ResponseEntity<CustomPaginationResponse<ArticleListResponse>> listArticles(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "category", required = false) ArticleCategoryEnum category
    ) {
        CustomPaginationResponse<ArticleListResponse> response = articleListService.listArticles(page, limit, category);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{articleId}")
    @Operation(
            description = "Get article by id",
            summary = "Get article by id"
    )
    @PreAuthorize("permitAll()")
    public ResponseEntity<CustomResponse<ArticleResponse>> getArticleById(
            @PathVariable(name = "articleId")UUID articleId
    ) {
        ArticleResponse response = articleGetService.getArticle(articleId);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
