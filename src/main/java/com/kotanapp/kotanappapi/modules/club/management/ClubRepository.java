package com.kotanapp.kotanappapi.modules.club.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface ClubRepository extends JpaRepository<ClubDAO, UUID>, JpaSpecificationExecutor<ClubDAO> {
    Optional<ClubDAO> findByIdAndIsArchivedFalse(UUID id);
}