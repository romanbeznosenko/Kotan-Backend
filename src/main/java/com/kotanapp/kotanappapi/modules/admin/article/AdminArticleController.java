package com.kotanapp.kotanappapi.modules.admin.article;

import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleListResponse;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleRequest;
import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleResponse;
import com.kotanapp.kotanappapi.modules.admin.article.services.AdminCreateArticleService;
import com.kotanapp.kotanappapi.modules.admin.article.services.AdminEditArticleService;
import com.kotanapp.kotanappapi.modules.admin.article.services.AdminGetArticleService;
import com.kotanapp.kotanappapi.modules.admin.article.services.AdminListArticleService;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import com.kotanapp.kotanappapi.utils.enums.ArticleCategoryEnum;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/article")
@RequiredArgsConstructor
public class AdminArticleController {
    private final AdminCreateArticleService adminCreateArticleService;
    private final AdminListArticleService adminListArticleService;
    private final AdminGetArticleService adminGetArticleService;
    private final AdminEditArticleService adminEditArticleService;

    private static final String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"", "/"}, consumes = "multipart/form-data")
    @Operation(
            description = "Create article by admin",
            summary = "Create article by admin"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<UUID>> createArticle(
            @RequestPart(name = "requesst") @Valid AdminArticleRequest request,
            @RequestPart(name = "image", required = false)MultipartFile image,
            @RequestPart(name = "heroImage", required = false) MultipartFile heroImage
    ) throws IOException {
        UUID response = adminCreateArticleService.createArticle(request, image, heroImage);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    @Operation(
            description = "List articles by admin",
            summary = "List articles by admin"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomPaginationResponse<AdminArticleListResponse>> listArticles(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "category", required = false) ArticleCategoryEnum category
    ) {
        CustomPaginationResponse<AdminArticleListResponse> response = adminListArticleService.listArticles(page, limit, category);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/{articleId}")
    @Operation(
            description = "Get article by admin",
            summary = "Get article by admin"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<AdminArticleResponse>> getArticleById(
            @PathVariable(name = "articleId") UUID articleId
    ) {
        AdminArticleResponse response = adminGetArticleService.getArticle(articleId);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping(value = "/{articleId}", consumes = {"multipart/form-data"})
    @Operation(
            description = "Edit article by admin",
            summary = "Edit article by admin"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<UUID>> editArticle(
            @PathVariable(name = "articleId") UUID articleId,
            @RequestPart(name = "request") @Valid AdminArticleRequest request,
            @RequestPart(name = "image", required = false) MultipartFile image,
            @RequestPart(name = "heroImage", required = false) MultipartFile heroImage
    ) throws IOException {
        UUID response = adminEditArticleService.editArticle(articleId, request, image, heroImage);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
