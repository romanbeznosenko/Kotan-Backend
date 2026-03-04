package com.kotanapp.kotanappapi.modules.competition.management;

import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<CompetitionDAO, UUID> {
    Optional<CompetitionDAO> findByIdAndIsArchivedFalse(UUID id);
}