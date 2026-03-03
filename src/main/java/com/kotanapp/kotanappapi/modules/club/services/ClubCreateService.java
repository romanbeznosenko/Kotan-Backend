package com.kotanapp.kotanappapi.modules.club.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubMapper;
import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.club.models.ClubRequest;
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
public class ClubCreateService {
    private final ClubManager clubManager;
    private final ClubMapper clubMapper;
    private final StorageService storageService;

    private final static String FOLDER_NAME = "CLUB_LOGO";

    public void createClub(ClubRequest request, MultipartFile file) throws IOException {
        log.info("Creating new club");

        Club club = ClubBuilders.buildFromRequest(request);

        if (file != null) {
            String storageKey = storageService.generateStorageKey(UUID.randomUUID(), file, FOLDER_NAME);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(file.getBytes());
            storageService.uploadFile(storageKey, file.getContentType(), outputStream);
            club.setLogo(storageKey);
        }

        ClubDAO clubDAO = clubMapper.mapToEntity(club, new CycleAvoidingMappingContext());

        clubManager.saveToDatabase(clubDAO);
    }
}
