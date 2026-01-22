package com.kotanapp.kotanappapi.core.player.services;

import com.kotanapp.kotanappapi.core.player.management.PlayerManager;
import com.kotanapp.kotanappapi.core.player.management.PlayerNotFoundException;
import com.kotanapp.kotanappapi.core.player.models.PlayerResponse;
import com.kotanapp.kotanappapi.files.services.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerGetService {
    private final PlayerManager playerManager;
    private final StorageService storageService;

    public PlayerResponse getPlayer(UUID playerId) {
        log.info("Get player by id {}", playerId);

        return PlayerBuilders.buildResponse(
                playerManager.findById(playerId)
                        .orElseThrow(PlayerNotFoundException::new),
                storageService
        );
    }
}
