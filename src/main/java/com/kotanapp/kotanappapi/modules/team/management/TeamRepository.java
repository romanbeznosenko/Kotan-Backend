package com.kotanapp.kotanappapi.modules.team.management;

import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamDAO, UUID>, JpaSpecificationExecutor<TeamDAO> {
}