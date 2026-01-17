package com.kotanapp.kotanappapi.core.news.services;

import com.kotanapp.kotanappapi.core.news.models.*;
import com.kotanapp.kotanappapi.core.tags.models.Tag;
import com.kotanapp.kotanappapi.core.tags.models.TagResponse;
import com.kotanapp.kotanappapi.files.services.StorageService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewsBuilders {
    public static News buildFromRequest(NewsRequest request, String storageKey, List<Tag> tags){
        return News.builder()
                .newsId(NewsId.of(null))
                .title(request.title())
                .shortDescription(request.shortDescription())
                .content(request.content())
                .banner(storageKey)
                .tags(tags)
                .isArchived(false)
                .build();
    }

    public static NewsListResponse buildListResponse(NewsDAO newsDAO, StorageService storageService, List<TagResponse> tags){
        return NewsListResponse.builder()
                .id(newsDAO.getId())
                .title(newsDAO.getTitle())
                .shortDescription(newsDAO.getShortDescription())
                .createdAt(newsDAO.getCreatedAt())
                .banner(storageService.createPresignedGetUrl(newsDAO.getBanner()))
                .tags(tags)
                .build();
    }

    public static NewsResponse buildNewsResponse(NewsDAO newsDAO, StorageService storageService, List<TagResponse> tags){
        return NewsResponse.builder()
                .id(newsDAO.getId())
                .title(newsDAO.getTitle())
                .content(newsDAO.getContent())
                .banner(storageService.createPresignedGetUrl(newsDAO.getBanner()))
                .createdAt(newsDAO.getCreatedAt())
                .tags(tags)
                .build();
    }
}
