package com.kotanapp.kotanappapi.modules.club.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.club.management.ClubManager;
import com.kotanapp.kotanappapi.modules.club.management.ClubMapper;
import com.kotanapp.kotanappapi.modules.club.management.ClubNotFoundException;
import com.kotanapp.kotanappapi.modules.club.models.Club;
import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
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
public class ClubUploadLogoService {
    private final ClubManager clubManager;
    private final ClubMapper clubMapper;
    private final StorageService storageService;

    private final static String FOLDER_NAME = "CLUB_LOGO";

    public void uploadLogo(UUID clubId, MultipartFile file) throws IOException {
        log.info("Uploading logo for team with id: {}", clubId);

        ClubDAO clubDAO = clubManager.findById(clubId)
                .orElseThrow(ClubNotFoundException::new);
        Club club = clubMapper.mapToDomain(clubDAO, new CycleAvoidingMappingContext());

        String oldStorageKey = club.getLogo();
        String storageKey = storageService.generateStorageKey(UUID.randomUUID(), file, FOLDER_NAME);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(file.getBytes());
        storageService.uploadFile(storageKey, file.getContentType(), outputStream);

        storageService.deleteFile(oldStorageKey);
        club.setLogo(storageKey);

        clubDAO = clubMapper.mapToEntity(club, new CycleAvoidingMappingContext());
        clubManager.saveToDatabase(clubDAO);
    }
}
