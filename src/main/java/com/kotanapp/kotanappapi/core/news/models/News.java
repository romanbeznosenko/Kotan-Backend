package com.kotanapp.kotanappapi.core.news.models;

import com.kotanapp.kotanappapi.core.tags.models.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private NewsId newsId;
    private String title;
    private String shortDescription;
    private String content;
    private String banner;
    private List<Tag> tags;

    private Instant createdAt;
    private Instant updatedAt;

    private Boolean isArchived;
    private Instant archivedAt;
}
