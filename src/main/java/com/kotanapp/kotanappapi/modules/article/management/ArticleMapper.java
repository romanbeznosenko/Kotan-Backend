package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.Article;
import com.kotanapp.kotanappapi.modules.article.models.ArticleDAO;
import com.kotanapp.kotanappapi.modules.article.models.ArticleId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ArticleMapper {
    @Mapping(target = "id", expression = "java(toMap.getArticleId().getId())")
    ArticleDAO mapToEntity(Article toMap,
                           @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "articleId", source = "toMap", qualifiedByName = "longToObject")
    Article mapToDomain(ArticleDAO toMap,
                        @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default ArticleId fromLongToObject(ArticleDAO articleDAO) {
        return ArticleId.of(articleDAO.getId());
    }
}