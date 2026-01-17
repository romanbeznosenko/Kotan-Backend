package com.kotanapp.kotanappapi.core.tags.management;

import com.kotanapp.kotanappapi.core.tags.models.TagDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagManager {
    private final TagRepository tagRepository;

    public TagDAO saveToDatabase(TagDAO tag) {
        return tagRepository.save(tag);
    }

    public List<TagDAO> findAll() {
        return tagRepository.findAll();
    }
}