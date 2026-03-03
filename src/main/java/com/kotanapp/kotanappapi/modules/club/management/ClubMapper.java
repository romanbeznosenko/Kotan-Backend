package com.kotanapp.kotanappapi.modules.club.management;

import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.club.models.ClubId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ClubMapper {
    @Mapping(target = "id", expression = "java(toMap.getClubId().getId())")
    ClubDAO mapToEntity(Club toMap,
                        @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "clubId", source = "toMap", qualifiedByName = "longToObject")
    Club mapToDomain(ClubDAO toMap,
                     @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default ClubId fromLongToObject(ClubDAO clubDAO) {
        return ClubId.of(clubDAO.getId());
    }
}