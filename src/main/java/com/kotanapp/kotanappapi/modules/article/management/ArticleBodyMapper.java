package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleBody;
import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {ArticleMapper.class})
public interface ArticleBodyMapper {
    @Mapping(target = "id", expression = "java(toMap.getArticleBodyId().getId())")
    ArticleBodyDAO mapToEntity(ArticleBody toMap,
                               @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "articleBodyId", source = "toMap", qualifiedByName = "longToObject")
    ArticleBody mapToDomain(ArticleBodyDAO toMap,
                            @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default ArticleBodyId fromLongToObject(ArticleBodyDAO articleBodyDAO) {
        return ArticleBodyId.of(articleBodyDAO.getId());
    }
}