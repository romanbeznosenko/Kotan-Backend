package com.kotanapp.kotanappapi.core.news.services;

import com.kotanapp.kotanappapi.core.news.management.NewsManager;
import com.kotanapp.kotanappapi.core.news.management.NewsSpecifications;
import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import com.kotanapp.kotanappapi.core.news.models.NewsListPageResponse;
import com.kotanapp.kotanappapi.core.news.models.NewsListResponse;
import com.kotanapp.kotanappapi.core.tags.management.TagManager;
import com.kotanapp.kotanappapi.core.tags.management.TagNotFoundException;
import com.kotanapp.kotanappapi.core.tags.models.TagDAO;
import com.kotanapp.kotanappapi.core.tags.models.TagResponse;
import com.kotanapp.kotanappapi.core.tags.services.TagBuilders;
import com.kotanapp.kotanappapi.files.services.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsListService {
    private final NewsManager newsManager;
    private final StorageService storageService;
    private final TagManager tagManager;

    public NewsListPageResponse listAllNews(int page, int limit, String title, List<UUID> tagsId){
        log.info("Listing all news");

        PageRequest pageRequest = PageRequest.of(page - 1, limit);

        List<TagDAO> tagDAOs = tagsId.stream()
                .map(item -> tagManager.findById(item).orElseThrow(TagNotFoundException::new))
                .toList();

        Specification<NewsDAO> spec = NewsSpecifications.byTitle(title)
                .and(NewsSpecifications.byTag(tagDAOs));

        Page<NewsDAO> newsPage = newsManager.findAll(spec, pageRequest);


        List<NewsListResponse> data = newsPage.get()
                .map(item -> {
                    List<TagResponse> tags = item.getTags().stream()
                            .map(TagBuilders::buildResponse)
                            .toList();

                    return NewsBuilders.buildListResponse(item, storageService, tags);
                })
                .toList();

        return NewsListPageResponse.builder()
                .count(newsPage.getTotalElements())
                .data(data)
                .build();
    }
}
