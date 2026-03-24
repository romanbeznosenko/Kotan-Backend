package com.kotanapp.kotanappapi.modules.admin.club.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.club.models.ClubAdminListResponse;
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubSpecifications;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubAdminPageService {
    private final ClubManager clubManager;
    private final StorageService storageService;

    public CustomPaginationResponse<ClubAdminListResponse> pageClubs(
            int page, int limit,
            Boolean isOurClub,
            String name
    ) {
        log.info("Fetching all clubs...");

        Specification<ClubDAO> spec = ClubSpecifications.isNotArchived()
                .and(ClubSpecifications.byName(name))
                .and(ClubSpecifications.isOurClub(isOurClub));
        Page<ClubDAO> clubDAOPage = clubManager.findAll(spec, PageRequest.of(page - 1, limit));
        List<ClubAdminListResponse> clubAdminListResponses = clubDAOPage.get()
                .map(clubDAO -> ClubAdminBuilders.buildListResponse(clubDAO, storageService))
                .toList();

        return new CustomPaginationResponse<>(clubAdminListResponses, clubDAOPage.getTotalElements());
    }
}
