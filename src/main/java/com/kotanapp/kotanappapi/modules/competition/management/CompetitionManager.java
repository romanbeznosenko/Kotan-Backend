package com.kotanapp.kotanappapi.modules.competition.management;

import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompetitionManager {
    private final CompetitionRepository competitionRepository;

    public CompetitionDAO saveToDatabase(CompetitionDAO competition) {
        return competitionRepository.save(competition);
    }

    public Optional<CompetitionDAO> findById(UUID id) {
        return competitionRepository.findByIdAndIsArchivedFalse(id);
    }

    public Page<CompetitionDAO> findAll(Pageable pageable) {
        return competitionRepository.findAll(pageable);
    }
}