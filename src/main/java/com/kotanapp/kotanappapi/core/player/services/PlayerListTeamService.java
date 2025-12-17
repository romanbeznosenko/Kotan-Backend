package com.kotanapp.kotanappapi.core.player.services;

import com.kotanapp.kotanappapi.core.player.management.PlayerManager;
import com.kotanapp.kotanappapi.core.player.management.PlayerRepository;
import com.kotanapp.kotanappapi.core.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.core.player.models.PlayerListResponse;
import com.kotanapp.kotanappapi.core.player.models.PlayerPageResponse;
import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerListTeamService {
    private final PlayerManager playerManager;
    private final TeamManager teamManager;

    public PlayerPageResponse listPlayersByTeam(UUID teamId){
        log.info("List players from team with id: {}", teamId);

        TeamDAO teamDAO = teamManager.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);
        Page<PlayerDAO> playerDAOPage = playerManager.findByTeam(teamDAO);
        List<PlayerListResponse> playerDAOList = playerDAOPage.get()
                .map(PlayerBuilders::buildListResponse)
                .toList();

        return PlayerPageResponse.builder()
                .count(playerDAOPage.getTotalElements())
                .data(playerDAOList)
                .build();
    }
}
