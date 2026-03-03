package com.kotanapp.kotanappapi.modules.club.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubManager {
    private final ClubRepository clubRepository;

    public ClubDAO saveToDatabase(ClubDAO club) {
        return clubRepository.save(club);
    }
}