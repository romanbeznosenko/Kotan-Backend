package com.kotanapp.kotanappapi.modules.playerTeamHistory.management;

import com.kotanapp.kotanappapi.modules.player.management.PlayerMapper;
import com.kotanapp.kotanappapi.modules.playerTeamHistory.models.PlayerHistory;
import com.kotanapp.kotanappapi.modules.playerTeamHistory.models.PlayerHistoryDAO;
import com.kotanapp.kotanappapi.modules.playerTeamHistory.models.PlayerHistoryId;
import com.kotanapp.kotanappapi.modules.team.management.TeamMapper;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {TeamMapper.class, PlayerMapper.class})
public interface PlayerHistoryMapper {
    @Mapping(target = "id", expression = "java(toMap.getPlayerHistoryId().getId())")
    PlayerHistoryDAO mapToEntity(PlayerHistory toMap,
                                 @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "playerHistoryId", source = "toMap", qualifiedByName = "longToObject")
    PlayerHistory mapToDomain(PlayerHistoryDAO toMap,
                              @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default PlayerHistoryId fromLongToObject(PlayerHistoryDAO playerHistoryDAO) {
        return PlayerHistoryId.of(playerHistoryDAO.getId());
    }
}