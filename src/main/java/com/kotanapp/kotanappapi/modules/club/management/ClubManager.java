package com.kotanapp.kotanappapi.modules.club.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubManager {
    private final ClubRepository clubRepository;

    public ClubDAO saveToDatabase(ClubDAO club) {
        return clubRepository.save(club);
    }

    public Page<ClubDAO> findAll(Specification<ClubDAO> specification, Pageable pageable) {
        return clubRepository.findAll(specification, pageable);
    }
}