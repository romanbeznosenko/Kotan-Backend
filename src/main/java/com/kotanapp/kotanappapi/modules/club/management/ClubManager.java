package com.kotanapp.kotanappapi.modules.club.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClubManager {
    private final ClubRepository clubRepository;

    public ClubDAO saveToDatabase(ClubDAO club) {
        return clubRepository.save(club);
    }

    public Optional<ClubDAO> findById(UUID id) {
        return clubRepository.findById(id);
    }
}