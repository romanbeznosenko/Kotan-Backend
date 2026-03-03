package com.kotanapp.kotanappapi.modules.playerMatchStatistics.management;

import com.kotanapp.kotanappapi.modules.match.management.MatchMapper;
import com.kotanapp.kotanappapi.modules.player.management.PlayerMapper;
import com.kotanapp.kotanappapi.modules.playerMatchStatistics.models.PlayerMatchStatistics;
import com.kotanapp.kotanappapi.modules.playerMatchStatistics.models.PlayerMatchStatisticsDAO;
import com.kotanapp.kotanappapi.modules.playerMatchStatistics.models.PlayerMatchStatisticsId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {MatchMapper.class, PlayerMapper.class})
public interface PlayerMatchStatisticsMapper {
    @Mapping(target = "id", expression = "java(toMap.getPlayerMatchStatisticsId().getId())")
    PlayerMatchStatisticsDAO mapToEntity(PlayerMatchStatistics toMap,
                                         @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "playerMatchStatisticsId", source = "toMap", qualifiedByName = "longToObject")
    PlayerMatchStatistics mapToDomain(PlayerMatchStatisticsDAO toMap,
                                      @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default PlayerMatchStatisticsId fromLongToObject(PlayerMatchStatisticsDAO playerMatchStatisticsDAO) {
        return PlayerMatchStatisticsId.of(playerMatchStatisticsDAO.getId());
    }
}