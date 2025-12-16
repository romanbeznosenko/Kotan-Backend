package com.kotanapp.kotanappapi.core.accountActivation.management;

import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCode;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeDAO;
import com.kotanapp.kotanappapi.core.accountActivation.models.VerificationCodeId;
import com.kotanapp.kotanappapi.core.authAccount.management.AuthAccountMapper;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {AuthAccountMapper.class})
public interface VerificationCodeMapper {
    @Mapping(target = "id", expression = "java(toMap.getVerificationCodeId().getId())")
    VerificationCodeDAO mapToEntity(VerificationCode toMap,
                                    @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "verificationCodeId", source = "toMap", qualifiedByName = "longToObject")
    VerificationCode mapToDomain(VerificationCodeDAO toMap,
                                 @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default VerificationCodeId fromLongToObject(VerificationCodeDAO verificationCodeDAO) {
        return VerificationCodeId.of(verificationCodeDAO.getId());
    }
}

