package com.kotanapp.kotanappapi.core.user.management;

import com.kotanapp.kotanappapi.core.user.models.User;
import com.kotanapp.kotanappapi.core.user.models.UserDAO;
import com.kotanapp.kotanappapi.core.user.models.UserId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {})
public interface UserMapper {
    @Mapping(target = "id", expression = "java(toMap.getUserId().getId())")
    UserDAO mapToEntity(User toMap,
                        @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "userId", source = "toMap", qualifiedByName = "longToObject")
    User mapToDomain(UserDAO toMap,
                     @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default UserId fromLongToObject(UserDAO userDAO) {
        return UserId.of(userDAO.getId());
    }
}

