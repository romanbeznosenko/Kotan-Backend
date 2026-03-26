package com.kotanapp.kotanappapi.modules.player.management;

import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
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
public class PlayerManager {
    private final PlayerRepository playerRepository;

    public PlayerDAO saveToDatabase(PlayerDAO player) {
        return playerRepository.save(player);
    }

    public List<PlayerDAO> findAll(Specification<PlayerDAO> spec) {
        return playerRepository.findAll(spec);
    }

    public Page<PlayerDAO> findAll(Specification<PlayerDAO> spec, Pageable pageable) {
        return playerRepository.findAll(spec, pageable);
    }

    public Optional<PlayerDAO> findById(UUID playerId) {
        return playerRepository.findById(playerId);
    }
}