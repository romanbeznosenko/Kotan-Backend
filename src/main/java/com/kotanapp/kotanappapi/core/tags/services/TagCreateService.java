package com.kotanapp.kotanappapi.core.tags.services;

import com.kotanapp.kotanappapi.core.tags.management.TagManager;
import com.kotanapp.kotanappapi.core.tags.management.TagMapper;
import com.kotanapp.kotanappapi.core.tags.models.Tag;
import com.kotanapp.kotanappapi.core.tags.models.TagDAO;
import com.kotanapp.kotanappapi.core.tags.models.TagRequest;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagCreateService {
    private final TagManager tagManager;
    private final TagMapper tagMapper;

    public void createTag(TagRequest request){
        log.info("Creating new tag");

        Tag tag = TagBuilders.buildFromRequest(request);
        TagDAO tagDAO = tagMapper.mapToEntity(tag, new CycleAvoidingMappingContext());

        tagManager.saveToDatabase(tagDAO);
    }
}
