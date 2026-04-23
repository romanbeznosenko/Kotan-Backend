package com.kotanapp.kotanappapi.modules.article;

import com.kotanapp.kotanappapi.modules.article.models.ArticleListResponse;
import com.kotanapp.kotanappapi.modules.article.services.ArticleListService;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleListService articleListService;

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
}
