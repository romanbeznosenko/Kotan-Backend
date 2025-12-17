package com.kotanapp.kotanappapi.core.match.management;

import com.kotanapp.kotanappapi.core.match.models.MatchDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MatchManager {
    private final MatchRepository matchRepository;

    public MatchDAO saveToDatabase(MatchDAO match) {
        return matchRepository.save(match);
    }
}