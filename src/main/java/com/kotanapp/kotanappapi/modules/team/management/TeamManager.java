package com.kotanapp.kotanappapi.modules.team.management;

import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamManager {
    private final TeamRepository teamRepository;

    public TeamDAO saveToDatabase(TeamDAO team) {
        return teamRepository.save(team);
    }
}