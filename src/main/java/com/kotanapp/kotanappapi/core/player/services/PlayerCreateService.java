package com.kotanapp.kotanappapi.core.player.services;

import com.kotanapp.kotanappapi.core.player.management.PlayerManager;
import com.kotanapp.kotanappapi.core.player.management.PlayerMapper;
import com.kotanapp.kotanappapi.core.player.models.Player;
import com.kotanapp.kotanappapi.core.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.core.player.models.PlayerRequest;
import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.management.TeamMapper;
import com.kotanapp.kotanappapi.core.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.core.team.models.Team;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerCreateService {
    private final PlayerManager playerManager;
    private final PlayerMapper playerMapper;
    private final TeamManager teamManager;
    private final TeamMapper teamMapper;

    public void createPlayer(UUID teamId, PlayerRequest request) {
        log.info("Creating player for team: {}", teamId);

        TeamDAO teamDAO = teamManager.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);
        Team team = teamMapper.mapToDomain(teamDAO, new CycleAvoidingMappingContext());

        Player player = PlayerBuilders.buildFromRequest(request, team);
        PlayerDAO playerDAO = playerMapper.mapToEntity(player, new CycleAvoidingMappingContext());

        playerManager.saveToDatabase(playerDAO);
    }
}
