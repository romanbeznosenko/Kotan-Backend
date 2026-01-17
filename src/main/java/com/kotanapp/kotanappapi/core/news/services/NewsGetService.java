package com.kotanapp.kotanappapi.core.news.services;

import com.kotanapp.kotanappapi.core.news.management.NewsManager;
import com.kotanapp.kotanappapi.core.news.management.NewsNotFoundException;
import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import com.kotanapp.kotanappapi.core.news.models.NewsResponse;
import com.kotanapp.kotanappapi.files.services.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsGetService {
    private final NewsManager newsManager;
    private final StorageService storageService;

    public NewsResponse getNewsById(UUID id) {
        log.info("Getting news with id {}", id);

        NewsDAO newsDAO = newsManager.findById(id)
                .orElseThrow(NewsNotFoundException::new);

        return NewsBuilders.buildNewsResponse(newsDAO, storageService);
    }
}
