package com.kotanapp.kotanappapi.core.team.management;

import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.enums.TeamTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<TeamDAO, UUID> {
    Optional<TeamDAO> findByNameAndTeamType(String name, TeamTypeEnum teamType);
}