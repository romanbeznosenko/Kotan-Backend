package com.kotanapp.kotanappapi.core.news.services;

import com.kotanapp.kotanappapi.core.news.management.NewsManager;
import com.kotanapp.kotanappapi.core.news.management.NewsMapper;
import com.kotanapp.kotanappapi.core.news.models.News;
import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import com.kotanapp.kotanappapi.core.news.models.NewsRequest;
import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsCreateService {
    private final NewsManager newsManager;
    private final NewsMapper newsMapper;
    private final StorageService storageService;
    private final static String FOLDER_NAME = "news";

    public void createNews(NewsRequest request, MultipartFile file) throws IOException {
        log.info("Creating news");
        News news = NewsBuilders.buildFromRequest(request, null);

        String storageKey = storageService.generateStorageKey(news.getNewsId().getId(), file,  FOLDER_NAME);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(file.getBytes());
        storageService.uploadFile(storageKey, file.getContentType(),  outputStream);

        news.setBanner(storageKey);

        NewsDAO newsDAO = newsMapper.mapToEntity(news, new CycleAvoidingMappingContext());

        newsManager.saveToDatabase(newsDAO);
    }
}
