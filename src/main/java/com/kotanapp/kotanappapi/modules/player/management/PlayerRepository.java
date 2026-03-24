package com.kotanapp.kotanappapi.modules.player.management;

import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerRepository extends JpaRepository<PlayerDAO, UUID> {
}