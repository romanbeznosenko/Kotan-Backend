package com.kotanapp.kotanappapi.modules.team.management;

import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamDAO, UUID> {
}