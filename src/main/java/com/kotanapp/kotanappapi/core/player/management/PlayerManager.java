package com.kotanapp.kotanappapi.core.player.management;

import com.kotanapp.kotanappapi.core.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerManager {
    private final PlayerRepository playerRepository;

    public PlayerDAO saveToDatabase(PlayerDAO player) {
        return playerRepository.save(player);
    }

    public Page<PlayerDAO> findByTeam(TeamDAO team) {
        Specification<PlayerDAO> spec = Specification.where(
                PlayerSpecifications.findByTeam(team)
        );
        Pageable pageable = PageRequest.of(0, Integer.MAX_VALUE);

        return playerRepository.findAll(spec, pageable);
    }

    public Optional<PlayerDAO> findById(UUID playerId) {
        return playerRepository.findById(playerId);
    }
}