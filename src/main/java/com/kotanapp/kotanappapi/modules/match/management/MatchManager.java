package com.kotanapp.kotanappapi.modules.match.management;

import com.kotanapp.kotanappapi.modules.match.models.MatchDAO;
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