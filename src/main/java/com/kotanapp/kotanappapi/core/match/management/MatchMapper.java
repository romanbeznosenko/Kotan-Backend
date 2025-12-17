package com.kotanapp.kotanappapi.core.match.management;

import com.kotanapp.kotanappapi.core.match.models.Match;
import com.kotanapp.kotanappapi.core.match.models.MatchDAO;
import com.kotanapp.kotanappapi.core.match.models.MatchId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MatchMapper {
    @Mapping(target = "id", expression = "java(toMap.getMatchId().getId())")
    MatchDAO mapToEntity(Match toMap,
                         @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "matchId", source = "toMap", qualifiedByName = "longToObject")
    Match mapToDomain(MatchDAO toMap,
                      @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default MatchId fromLongToObject(MatchDAO matchDAO) {
        return MatchId.of(matchDAO.getId());
    }
}