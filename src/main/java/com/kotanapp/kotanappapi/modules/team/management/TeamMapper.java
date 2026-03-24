package com.kotanapp.kotanappapi.modules.team.management;

import com.kotanapp.kotanappapi.modules.club.management.ClubMapper;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.club.models.ClubId;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamId;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), uses = {ClubMapper.class})
public interface TeamMapper {
    @Mapping(target = "id", expression = "java(toMap.getTeamId().getId())")
    @Mapping(target = "club", source = "toMap.club", qualifiedByName = "clubIdToClubDao")
    TeamDAO mapToEntity(Team toMap,
                        @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Mapping(target = "teamId", source = "toMap", qualifiedByName = "longToObject")
    @Mapping(target = "club", source = "toMap.club", qualifiedByName = "clubDaoToClubId")
    Team mapToDomain(TeamDAO toMap,
                     @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    @Named("longToObject")
    default TeamId fromLongToObject(TeamDAO teamDAO) {
        return TeamId.of(teamDAO.getId());
    }

    @Named("clubDaoToClubId")
    default ClubId clubDaoToClubId(ClubDAO clubDAO) {
        if (clubDAO == null) return null;
        return ClubId.of(clubDAO.getId());
    }

    @Named("clubIdToClubDao")
    default ClubDAO clubIdToClubDao(ClubId clubId) {
        if (clubId == null) return null;
        return ClubDAO.builder().id(clubId.getId()).build();
    }
}