package com.kotanapp.kotanappapi.core.player.services;

import com.kotanapp.kotanappapi.core.player.management.PlayerManager;
import com.kotanapp.kotanappapi.core.player.management.PlayerMapper;
import com.kotanapp.kotanappapi.core.player.management.PlayerNotFoundException;
import com.kotanapp.kotanappapi.core.player.models.Player;
import com.kotanapp.kotanappapi.core.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.files.services.StorageService;
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
public class PlayerUpdateAvatarService {
    private final PlayerManager playerManager;
    private final PlayerMapper playerMapper;
    private final StorageService storageService;

    private final static String FOLDER_NAME = "avatar";

    public void updatePlayerAvatar(UUID playerId, MultipartFile file) throws IOException {
        log.info("Updating avatar for player with id: {}", playerId);

        PlayerDAO playerDAO = playerManager.findById(playerId)
                .orElseThrow(PlayerNotFoundException::new);
        Player player = playerMapper.mapToDomain(playerDAO, new CycleAvoidingMappingContext());

        String oldStorageKey = player.getAvatar();
        String storageKey = storageService.generateStorageKey(playerDAO.getId(), file,  FOLDER_NAME);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(file.getBytes());
        storageService.uploadFile(storageKey, file.getContentType(),  outputStream);

        if (oldStorageKey != null && !oldStorageKey.isEmpty()) {
            storageService.deleteFile(oldStorageKey);
        }

        player.setAvatar(storageKey);
        playerDAO = playerMapper.mapToEntity(player, new CycleAvoidingMappingContext());
        playerManager.saveToDatabase(playerDAO);
    }
}
