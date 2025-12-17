package com.kotanapp.kotanappapi.core.team.services;

import com.kotanapp.kotanappapi.core.team.management.TeamManager;
import com.kotanapp.kotanappapi.core.team.management.TeamNotFoundException;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import com.kotanapp.kotanappapi.files.services.StorageService;
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
public class TeamUploadLogoService {
    private final TeamManager teamManager;
    private final StorageService storageService;

    private final static String STORAGE_FOLDER_NAME = "logo";

    public void uploadLogo(UUID teamId, MultipartFile file) throws IOException {
        log.info("Uploading logo to team {}", teamId);

        TeamDAO teamDAO = teamManager.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);

        String storageKey = storageService.generateStorageKey(teamDAO.getId(), file, STORAGE_FOLDER_NAME);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(file.getBytes());
        storageService.uploadFile(storageKey, file.getContentType(),  outputStream);

        teamManager.saveToDatabase(teamDAO);
    }
}
