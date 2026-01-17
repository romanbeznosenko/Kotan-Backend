package com.kotanapp.kotanappapi.core.news.management;

import com.kotanapp.kotanappapi.core.news.models.News;
import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import com.kotanapp.kotanappapi.core.news.models.NewsId;
import com.kotanapp.kotanappapi.core.tags.management.TagMapper;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {TagMapper.class})
public interface NewsMapper {
    @Mapping(target = "id", expression = "java(toMap.getNewsId().getId())")
    NewsDAO mapToEntity(News toMap,
                        @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "newsId", source = "toMap", qualifiedByName = "longToObject")
    News mapToDomain(NewsDAO toMap,
                     @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default NewsId fromLongToObject(NewsDAO newsDAO) {
        return NewsId.of(newsDAO.getId());
    }
}