package com.kotanapp.kotanappapi.modules.player.management;

import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerManager {
    private final PlayerRepository playerRepository;

    public PlayerDAO saveToDatabase(PlayerDAO player) {
        return playerRepository.save(player);
    }
}