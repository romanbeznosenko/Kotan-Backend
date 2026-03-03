package com.kotanapp.kotanappapi.modules.match.management;

import com.kotanapp.kotanappapi.modules.match.models.MatchDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchRepository extends JpaRepository<MatchDAO, UUID> {
}