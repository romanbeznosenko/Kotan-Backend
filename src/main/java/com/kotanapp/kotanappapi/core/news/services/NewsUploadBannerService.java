package com.kotanapp.kotanappapi.core.news.services;

import com.kotanapp.kotanappapi.core.news.management.NewsManager;
import com.kotanapp.kotanappapi.core.news.management.NewsMapper;
import com.kotanapp.kotanappapi.core.news.management.NewsNotFoundException;
import com.kotanapp.kotanappapi.core.news.models.News;
import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import com.kotanapp.kotanappapi.files.services.StorageService;
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
public class NewsUploadBannerService {
    private final NewsManager newsManager;
    private final NewsMapper newsMapper;
    private final StorageService storageService;

    private final static String FOLDER_NAME = "banner";

    public void uploadBanner(UUID newsId, MultipartFile file) throws IOException {
        log.info("Uploading banner for news with id: {}", newsId);

        NewsDAO newsDAO = newsManager.findById(newsId)
                .orElseThrow(NewsNotFoundException::new);
        News news = newsMapper.mapToDomain(newsDAO, new CycleAvoidingMappingContext());
        news = updateNews(news, file);
        newsDAO = newsMapper.mapToEntity(news, new CycleAvoidingMappingContext());

        newsManager.saveToDatabase(newsDAO);
    }

    private News updateNews(News news, MultipartFile file) throws IOException {
        String storageKey = storageService.generateStorageKey(news.getNewsId().getId(), file, FOLDER_NAME);
        String oldStorageKey = news.getBanner();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(file.getBytes());
        storageService.uploadFile(storageKey, file.getContentType(),  outputStream);

        storageService.deleteFile(oldStorageKey);

        news.setBanner(storageKey);

        return news;
    }
}
