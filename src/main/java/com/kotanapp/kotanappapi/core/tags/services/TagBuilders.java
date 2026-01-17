package com.kotanapp.kotanappapi.core.tags.services;

import com.kotanapp.kotanappapi.core.tags.models.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TagBuilders {
    public static Tag buildFromRequest(TagRequest request) {
        return Tag.builder()
                .tagId(TagId.of(null))
                .name(request.name())
                .isArchived(false)
                .build();
    }

    public static TagResponse buildResponse(TagDAO tagDAO){
        return TagResponse.builder()
                .id(tagDAO.getId())
                .name(tagDAO.getName())
                .build();
    }
}
