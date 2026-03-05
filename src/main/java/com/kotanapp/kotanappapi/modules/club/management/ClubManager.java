package com.kotanapp.kotanappapi.modules.club.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
        return clubRepository.findByIdAndIsArchivedFalse(id);
    }

    public Page<ClubDAO> findAll(Pageable pageable) {
        Specification<ClubDAO> specification = ClubSpecifications.notIsArchived();

        Sort sortByName = pageable.getSort().isSorted()
                ? pageable.getSort()
                : Sort.by(Sort.Direction.ASC, "name");

        Pageable pageableWithSort = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                sortByName
        );

        return clubRepository.findAll(specification, pageableWithSort);
    }

    public Optional<ClubDAO> findByName(String name) {
        Specification<ClubDAO> specification = ClubSpecifications.byName(name)
                .and(ClubSpecifications.notIsArchived());
        return clubRepository.findOne(specification);
    }
}