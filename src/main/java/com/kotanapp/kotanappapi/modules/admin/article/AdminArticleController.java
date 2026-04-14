package com.kotanapp.kotanappapi.modules.admin.article;

import com.kotanapp.kotanappapi.modules.admin.article.models.AdminArticleRequest;
import com.kotanapp.kotanappapi.modules.admin.article.services.AdminCreateArticleService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/article")
@RequiredArgsConstructor
public class AdminArticleController {
    private final AdminCreateArticleService adminCreateArticleService;

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
}
