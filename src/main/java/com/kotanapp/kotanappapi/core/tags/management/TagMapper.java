package com.kotanapp.kotanappapi.core.tags.management;

import com.kotanapp.kotanappapi.core.tags.modesl.Tag;
import com.kotanapp.kotanappapi.core.tags.modesl.TagDAO;
import com.kotanapp.kotanappapi.core.tags.modesl.TagId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface TagMapper {
    @Mapping(target = "id", expression = "java(toMap.getTagId().getId())")
    TagDAO mapToEntity(Tag toMap,
                       @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "tagId", source = "toMap", qualifiedByName = "longToObject")
    Tag mapToDomain(TagDAO toMap,
                    @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default TagId fromLongToObject(TagDAO tagDAO) {
        return TagId.of(tagDAO.getId());
    }
}