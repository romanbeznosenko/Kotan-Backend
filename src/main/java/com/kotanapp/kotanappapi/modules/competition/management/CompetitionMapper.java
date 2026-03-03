package com.kotanapp.kotanappapi.modules.competition.management;

import com.kotanapp.kotanappapi.modules.competition.models.Competition;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface CompetitionMapper {
    @Mapping(target = "id", expression = "java(toMap.getCompetitionId().getId())")
    CompetitionDAO mapToEntity(Competition toMap,
                               @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "competitionId", source = "toMap", qualifiedByName = "longToObject")
    Competition mapToDomain(CompetitionDAO toMap,
                            @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default CompetitionId fromLongToObject(CompetitionDAO competitionDAO) {
        return CompetitionId.of(competitionDAO.getId());
    }
}