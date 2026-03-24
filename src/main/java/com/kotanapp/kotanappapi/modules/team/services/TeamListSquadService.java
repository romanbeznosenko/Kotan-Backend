package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.player.management.PlayerManager;
import com.kotanapp.kotanappapi.modules.player.management.PlayerSpecifications;
import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.modules.player.models.PlayerSquadResponse;
import com.kotanapp.kotanappapi.modules.player.services.PlayerBuilders;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamListSquadService {
    private final TeamManager teamManager;
    private final PlayerManager playerManager;
    private final StorageService storageService;

    public List<PlayerSquadResponse> listSquadPlayers(UUID teamId){
        log.info("Listing squad players from team with id: {}", teamId);

        TeamDAO teamDAO = teamManager.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);

        Specification<PlayerDAO> spec = PlayerSpecifications.isNotArchived()
                .and(PlayerSpecifications.byTeam(teamDAO));
        List<PlayerDAO> playerDAOList = playerManager.findAll(spec);

        return playerDAOList.stream()
                .map(playerDAO -> PlayerBuilders.buildSquadResponse(playerDAO, storageService))
                .toList();
    }
}
