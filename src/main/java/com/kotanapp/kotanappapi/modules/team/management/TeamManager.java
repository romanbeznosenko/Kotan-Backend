package com.kotanapp.kotanappapi.modules.team.management;

import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamManager {
    private final TeamRepository teamRepository;

    public TeamDAO saveToDatabase(TeamDAO team) {
        return teamRepository.save(team);
    }

    public List<TeamDAO> findAll(Specification<TeamDAO> specification) {
        return teamRepository.findAll(specification);
    }

    public Optional<TeamDAO> findById(UUID id) {
        return teamRepository.findById(id);
    }

    public Page<TeamDAO> findAll(Specification<TeamDAO> specification, Pageable pageable) {
        return teamRepository.findAll(specification, pageable);
    }
}