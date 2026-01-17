package com.kotanapp.kotanappapi.core.tags.management;

import com.kotanapp.kotanappapi.core.tags.modesl.TagDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagManager {
    private final TagRepository tagRepository;

    public TagDAO saveToDatabase(TagDAO tag) {
        return tagRepository.save(tag);
    }
}