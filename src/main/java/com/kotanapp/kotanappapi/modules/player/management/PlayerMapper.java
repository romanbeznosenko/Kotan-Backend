package com.kotanapp.kotanappapi.modules.player.management;

import com.kotanapp.kotanappapi.modules.player.models.Player;
import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.modules.player.models.PlayerId;
import com.kotanapp.kotanappapi.modules.team.management.TeamMapper;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {TeamMapper.class})
public interface PlayerMapper {
    @Mapping(target = "id", expression = "java(toMap.getPlayerId().getId())")
    PlayerDAO mapToEntity(Player toMap,
                          @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "playerId", source = "toMap", qualifiedByName = "longToObject")
    Player mapToDomain(PlayerDAO toMap,
                       @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default PlayerId fromLongToObject(PlayerDAO playerDAO) {
        return PlayerId.of(playerDAO.getId());
    }
}