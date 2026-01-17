package com.kotanapp.kotanappapi.core.tags.services;

import com.kotanapp.kotanappapi.core.tags.management.TagManager;
import com.kotanapp.kotanappapi.core.tags.models.TagListResponse;
import com.kotanapp.kotanappapi.core.tags.models.TagResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagListService {
    private final TagManager tagManager;

    public TagListResponse listTags() {
        log.info("Listing all tags");

        List<TagResponse> data = tagManager.findAll().stream()
                .map(TagBuilders::buildResponse)
                .toList();

        return TagListResponse.builder()
                .count(data.size())
                .data(data)
                .build();
    }
}
