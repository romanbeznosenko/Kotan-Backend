package com.kotanapp.kotanappapi.core.news.services;

import com.kotanapp.kotanappapi.core.news.management.NewsManager;
import com.kotanapp.kotanappapi.core.news.management.NewsMapper;
import com.kotanapp.kotanappapi.core.news.management.NewsNotFoundException;
import com.kotanapp.kotanappapi.core.news.models.News;
import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import com.kotanapp.kotanappapi.core.news.models.NewsRequest;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsEditService {
    private final NewsManager newsManager;
    private final NewsMapper newsMapper;

    public void editNews(NewsRequest request, UUID newsId){
        log.info("Editing news with id: {}", newsId);

        NewsDAO newsDAO = newsManager.findById(newsId)
                .orElseThrow(NewsNotFoundException::new);
        News news = newsMapper.mapToDomain(newsDAO, new CycleAvoidingMappingContext());
        news = updateNews(news, request);

        newsDAO = newsMapper.mapToEntity(news, new CycleAvoidingMappingContext());

        newsManager.saveToDatabase(newsDAO);
    }

    private News updateNews(News news, NewsRequest request){
        news.setTitle(news.getTitle());
        news.setShortDescription(news.getShortDescription());
        news.setContent(news.getContent());

        return news;
    }
}
