package com.kotanapp.kotanappapi.core.team.management;

import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamDAO, UUID> {
}