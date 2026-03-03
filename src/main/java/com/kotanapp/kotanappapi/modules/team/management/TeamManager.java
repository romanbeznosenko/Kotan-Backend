package com.kotanapp.kotanappapi.modules.team.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamManager {
    private final TeamRepository teamRepository;

    public TeamDAO saveToDatabase(TeamDAO team) {
        return teamRepository.save(team);
    }

    public Optional<TeamDAO> findByIdAndClub(UUID id, ClubDAO club) {
        return teamRepository.findByIdAndClubAndIsArchivedFalse(id, club);
    }

    public Page<TeamDAO> findAll(Specification<TeamDAO> spec, Pageable pageable) {
        return teamRepository.findAll(spec, pageable);
    }
}