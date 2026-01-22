package com.kotanapp.kotanappapi.core.player.services;

import com.kotanapp.kotanappapi.core.player.management.PlayerManager;
import com.kotanapp.kotanappapi.core.player.management.PlayerMapper;
import com.kotanapp.kotanappapi.core.player.management.PlayerNotFoundException;
import com.kotanapp.kotanappapi.core.player.models.Player;
import com.kotanapp.kotanappapi.core.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.core.player.models.PlayerRequest;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerEditService {
    private final PlayerManager playerManager;
    private final PlayerMapper playerMapper;

    public void editPlayer(UUID playerId, PlayerRequest request) {
        log.info("Editing player with id: {}", playerId);

        PlayerDAO playerDAO = playerManager.findById(playerId)
                .orElseThrow(PlayerNotFoundException::new);
        Player player = playerMapper.mapToDomain(playerDAO, new CycleAvoidingMappingContext());

        player.setFirstName(player.getFirstName());
        player.setLastName(player.getLastName());
        player.setPlayerPosition(player.getPlayerPosition());
        player.setFieldNumber(player.getFieldNumber());
        player.setBirthDate(player.getBirthDate());

        playerDAO = playerMapper.mapToEntity(player, new CycleAvoidingMappingContext());
        playerManager.saveToDatabase(playerDAO);
    }
}
