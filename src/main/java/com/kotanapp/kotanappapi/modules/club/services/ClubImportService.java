package com.kotanapp.kotanappapi.modules.club.services;

import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubMapper;
import com.kotanapp.kotanappapi.modules.club.management.ClubSpecifications;
import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import com.kotanapp.kotanappapi.utils.fileParser.FileParseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClubImportService {
    private final FileParseService fileParseService;
    private final ClubMapper clubMapper;
    private final ClubManager clubManager;

    public void importClubs(MultipartFile file) throws IOException {
        log.info("Importing clubs from file");

        List<Club> clubList = fileParseService.extractClubs(file);
        clubList.stream()
                .filter(item -> {
                    Specification<ClubDAO> spec = ClubSpecifications.byName(item.getName())
                            .and(ClubSpecifications.notIsArchived());
                    return clubManager.findOne(spec).isEmpty();
                })
                .map(item -> clubMapper.mapToEntity(item, new CycleAvoidingMappingContext()))
                .forEach(clubManager::saveToDatabase);

    }
}
