package com.kotanapp.kotanappapi.modules.playerMatchStatistics.management;

import com.kotanapp.kotanappapi.modules.playerMatchStatistics.models.PlayerMatchStatisticsDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerMatchStatisticsRepository extends JpaRepository<PlayerMatchStatisticsDAO, UUID> {
}