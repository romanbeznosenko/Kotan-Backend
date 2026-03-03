package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamMapper;
import com.kotanapp.kotanappapi.modules.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamDeleteService {
    private final TeamManager teamManager;
    private final ClubManager clubManager;
    private final TeamMapper teamMapper;

    public void deleteTeam(UUID clubId, UUID teamId) {
        log.info("Deleting team with id: {}, from club with id: {}", teamId, clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);

        TeamDAO teamDAO = teamManager.findByIdAndClub(teamId, clubDAO)
                .orElseThrow(TeamNotFoundException::new);

        Team team = teamMapper.mapToDomain(teamDAO, new CycleAvoidingMappingContext());
        team.setIsArchived(true);
        team.setArchivedAt(Instant.now());

        teamDAO = teamMapper.mapToEntity(team, new CycleAvoidingMappingContext());
        teamManager.saveToDatabase(teamDAO);
    }
}
