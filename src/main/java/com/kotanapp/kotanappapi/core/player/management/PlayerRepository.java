package com.kotanapp.kotanappapi.core.player.management;

import com.kotanapp.kotanappapi.core.player.models.PlayerDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PlayerRepository extends JpaRepository<PlayerDAO, UUID>, JpaSpecificationExecutor<PlayerDAO> {
}