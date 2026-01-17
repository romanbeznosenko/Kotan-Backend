package com.kotanapp.kotanappapi.core.news.services;

import com.kotanapp.kotanappapi.core.news.management.NewsManager;
import com.kotanapp.kotanappapi.core.news.management.NewsMapper;
import com.kotanapp.kotanappapi.core.news.models.News;
import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import com.kotanapp.kotanappapi.core.news.models.NewsRequest;
import com.kotanapp.kotanappapi.core.tags.management.TagManager;
import com.kotanapp.kotanappapi.core.tags.management.TagMapper;
import com.kotanapp.kotanappapi.core.tags.management.TagNotFoundException;
import com.kotanapp.kotanappapi.core.tags.models.Tag;
import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsCreateService {
    private final NewsManager newsManager;
    private final NewsMapper newsMapper;
    private final StorageService storageService;
    private final TagManager tagManager;
    private final TagMapper tagMapper;
    private final static String FOLDER_NAME = "news";

    public void createNews(NewsRequest request, MultipartFile file, List<UUID> tagsId) throws IOException {
        log.info("Creating news");
        List<Tag> tags = tagsId.stream()
                .map(item -> tagManager.findById(item).orElseThrow(TagNotFoundException::new))
                .map(item -> tagMapper.mapToDomain(item, new CycleAvoidingMappingContext()))
                .toList();

        News news = NewsBuilders.buildFromRequest(request, null, tags);

        String storageKey = storageService.generateStorageKey(news.getNewsId().getId(), file,  FOLDER_NAME);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(file.getBytes());
        storageService.uploadFile(storageKey, file.getContentType(),  outputStream);

        news.setBanner(storageKey);

        NewsDAO newsDAO = newsMapper.mapToEntity(news, new CycleAvoidingMappingContext());

        newsManager.saveToDatabase(newsDAO);
    }
}
