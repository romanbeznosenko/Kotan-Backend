package com.kotanapp.kotanappapi.core.authAccount.management;

import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccount;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountDAO;
import com.kotanapp.kotanappapi.core.authAccount.models.AuthAccountId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AuthAccountMapper {
    @Mapping(target = "id", expression = "java(toMap.getAuthAccountId().getId())")
    AuthAccountDAO mapToEntity(AuthAccount toMap,
                               @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "authAccountId", source = "toMap", qualifiedByName = "longToObject")
    AuthAccount mapToDomain(AuthAccountDAO toMap,
                            @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default AuthAccountId fromLongToObject(AuthAccountDAO authAccountDAO) {
        return AuthAccountId.of(authAccountDAO.getId());
    }
}

