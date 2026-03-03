package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubMapper;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamMapper;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamRequest;
import com.kotanapp.kotanappapi.utils.CycleAvoidingMappingContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamCreateService {
    private final StorageService storageService;
    private final TeamManager teamManager;
    private final TeamMapper teamMapper;
    private final ClubManager clubManager;
    private final ClubMapper clubMapper;

    private final static String FOLDER_NAME = "TEAM_COVER_IMAGE";

    public void createTeam(UUID clubId, TeamRequest request, MultipartFile file) throws IOException {
        log.info("Creating new team for club with id: {}", clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);
        Club club = clubMapper.mapToDomain(clubDAO, new CycleAvoidingMappingContext());

        Team team = TeamBuilders.buildFromRequest(request, club);

        if (file != null) {
            String storageKey = storageService.generateStorageKey(UUID.randomUUID(), file, FOLDER_NAME);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(file.getBytes());
            storageService.uploadFile(storageKey, file.getContentType(), outputStream);
            team.setCoverImage(storageKey);
        }

        TeamDAO teamDAO = teamMapper.mapToEntity(team, new CycleAvoidingMappingContext());
        teamManager.saveToDatabase(teamDAO);
    }
}
