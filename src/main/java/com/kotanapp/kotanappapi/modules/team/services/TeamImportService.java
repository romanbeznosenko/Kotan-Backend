package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamMapper;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import com.kotanapp.kotanappapi.utils.fileParser.FileParseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamImportService {
    private final TeamManager teamManager;
    private final TeamMapper teamMapper;
    private final FileParseService fileParseService;

    public void importTeams(MultipartFile file) throws IOException {
        log.info("Importing teams from CSV");

        List<Team> teamList = fileParseService.extractTeams(file);
        teamList.stream()
                .map(item -> teamMapper.mapToEntity(item, new CycleAvoidingMappingContext()))
                .forEach(teamManager::saveToDatabase);
    }
}
