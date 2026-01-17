package com.kotanapp.kotanappapi.core.news.management;

import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsManager {
    private final NewsRepository newsRepository;

    public NewsDAO saveToDatabase(NewsDAO news) {
        return newsRepository.save(news);
    }

    public Page<NewsDAO> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable);
    }
}