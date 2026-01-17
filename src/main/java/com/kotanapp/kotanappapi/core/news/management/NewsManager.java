package com.kotanapp.kotanappapi.core.news.management;

import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsManager {
    private final NewsRepository newsRepository;

    public NewsDAO saveToDatabase(NewsDAO news) {
        return newsRepository.save(news);
    }
}