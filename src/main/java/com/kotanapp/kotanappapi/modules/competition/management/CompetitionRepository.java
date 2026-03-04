package com.kotanapp.kotanappapi.modules.competition.management;

import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface CompetitionRepository extends JpaRepository<CompetitionDAO, UUID>, JpaSpecificationExecutor<CompetitionDAO> {
    Optional<CompetitionDAO> findByIdAndIsArchivedFalse(UUID id);
}