package com.kotanapp.kotanappapi.modules.playerMatchStatistics.management;

import com.kotanapp.kotanappapi.modules.playerMatchStatistics.models.PlayerMatchStatisticsDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerMatchStatisticsManager {
    private final PlayerMatchStatisticsRepository playerMatchStatisticsRepository;

    public PlayerMatchStatisticsDAO saveToDatabase(PlayerMatchStatisticsDAO playerMatchStatistics) {
        return playerMatchStatisticsRepository.save(playerMatchStatistics);
    }
}