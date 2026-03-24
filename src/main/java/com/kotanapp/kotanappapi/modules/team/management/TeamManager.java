package com.kotanapp.kotanappapi.modules.team.management;

import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

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
}