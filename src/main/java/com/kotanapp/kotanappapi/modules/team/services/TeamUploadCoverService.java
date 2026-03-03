package com.kotanapp.kotanappapi.modules.team.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.management.TeamMapper;
import com.kotanapp.kotanappapi.modules.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.modules.team.models.Team;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
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
public class TeamUploadCoverService {
    private final TeamManager teamManager;
    private final TeamMapper teamMapper;
    private final ClubManager clubManager;
    private final StorageService storageService;

    private final static String FOLDER_NAME = "TEAM_COVER_IMAGE";

    public void uploadCover(UUID clubId, UUID teamId, MultipartFile file) throws IOException {
        log.info("Uploading cover image for team with id: {} for club with id: {}", teamId, clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);

        TeamDAO teamDAO = teamManager.findByIdAndClub(teamId, clubDAO)
                .orElseThrow(TeamNotFoundException::new);
        Team team = teamMapper.mapToDomain(teamDAO, new CycleAvoidingMappingContext());

        String oldStorageKey = team.getCoverImage();
        String storageKey = storageService.generateStorageKey(UUID.randomUUID(), file, FOLDER_NAME);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(file.getBytes());
        storageService.uploadFile(storageKey, file.getContentType(), outputStream);

        storageService.deleteFile(oldStorageKey);
        team.setCoverImage(storageKey);

        teamDAO = teamMapper.mapToEntity(team, new CycleAvoidingMappingContext());
        teamManager.saveToDatabase(teamDAO);
    }
}
