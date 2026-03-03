package com.kotanapp.kotanappapi.modules.playerTeamHistory.management;

import com.kotanapp.kotanappapi.modules.playerTeamHistory.models.PlayerHistoryDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerHistoryRepository extends JpaRepository<PlayerHistoryDAO, UUID> {
}