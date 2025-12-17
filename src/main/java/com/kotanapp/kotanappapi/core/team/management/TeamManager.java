package com.kotanapp.kotanappapi.core.team.management;

import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.enums.TeamTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Optional<TeamDAO> findById(UUID teamId) {
        return teamRepository.findById(teamId);
    }

    public Page<TeamDAO> findAll(Pageable pageable) {
        return teamRepository.findAll(pageable);
    }

    public Optional<TeamDAO> findByNameAndTeamType(String name, TeamTypeEnum teamType) {
        return teamRepository.findByNameAndTeamType(name, teamType);
    }
}