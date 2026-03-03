package com.kotanapp.kotanappapi.modules.playerTeamHistory.management;

import com.kotanapp.kotanappapi.modules.playerTeamHistory.models.PlayerHistoryDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerHistoryManager {
    private final PlayerHistoryRepository playerHistoryRepository;

    public PlayerHistoryDAO saveToDatabase(PlayerHistoryDAO playerHistory) {
        return playerHistoryRepository.save(playerHistory);
    }
}