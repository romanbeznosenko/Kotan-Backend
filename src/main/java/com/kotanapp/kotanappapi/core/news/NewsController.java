package com.kotanapp.kotanappapi.core.news;

import com.kotanapp.kotanappapi.core.news.models.NewsListPageResponse;
import com.kotanapp.kotanappapi.core.news.models.NewsRequest;
import com.kotanapp.kotanappapi.core.news.models.NewsResponse;
import com.kotanapp.kotanappapi.core.news.services.*;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsCreateService newsCreateService;
    private final NewsEditService newsEditService;
    private final NewsGetService newsGetService;
    private final NewsListService newsListService;
    private final NewsUploadBannerService newsUploadBannerService;

    private final static String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"", "/"}, consumes = "multipart/form-data")
    @Operation(
            description = "Create news",
            summary = "Create news"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> createNews(
            @RequestParam(name = "request") NewsRequest request,
            @RequestParam(name = "file") MultipartFile file
    ) throws IOException {
        newsCreateService.createNews(request, file);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @PatchMapping(value = "/{newsId}")
    @Operation(
            description = "Edit news",
            summary = "Edit news"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> editNews(
            @PathVariable(name = "newsId") UUID newsId,
            @RequestParam(name = "request") NewsRequest request
    ) {
        newsEditService.editNews(request, newsId);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    @Operation(
            description = "List all news",
            summary = "List all news"
    )
    @PreAuthorize("permitAll()")
    public ResponseEntity<CustomResponse<NewsListPageResponse>> listNews(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        NewsListPageResponse response = newsListService.listAllNews(page, limit);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/{newsId}")
    @Operation(
            description = "Get news by id",
            summary = "Get news by id"
    )
    @PreAuthorize("permitAll()")
    public ResponseEntity<CustomResponse<NewsResponse>> getNews(
            @PathVariable(name = "newsId") UUID newsId
    ) {
        NewsResponse response = newsGetService.getNewsById(newsId);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @PatchMapping(value = "/banner/{newsId}", consumes = "multipart/form-data")
    @Operation(
            description = "Upload banner for news",
            summary = "Upload banner for news"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> uploadBanner(
            @PathVariable(name = "newsId") UUID newsId,
            @RequestParam(name = "file") MultipartFile file
    ) throws IOException {
        newsUploadBannerService.uploadBanner(newsId, file);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
