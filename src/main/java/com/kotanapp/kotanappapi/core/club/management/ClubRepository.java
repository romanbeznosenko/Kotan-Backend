package com.kotanapp.kotanappapi.core.club.management;

import com.kotanapp.kotanappapi.core.club.models.ClubDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClubRepository extends JpaRepository<ClubDAO, UUID> {
}