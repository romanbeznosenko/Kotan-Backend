package com.kotanapp.kotanappapi.modules.admin.player.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.player.models.PlayerAdminRequest;
import com.kotanapp.kotanappapi.modules.player.management.PlayerManager;
import com.kotanapp.kotanappapi.modules.player.management.PlayerMapper;
import com.kotanapp.kotanappapi.modules.player.models.Player;
import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
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
public class PlayerAdminCreateService {
    private final TeamManager teamManager;
    private final TeamMapper teamMapper;
    private final PlayerManager playerManager;
    private final PlayerMapper playerMapper;
    private final StorageService storageService;

    private static final String FOLDER_NAME = "player_photo";

    public UUID createPlayer(UUID teamId, PlayerAdminRequest request, MultipartFile file) throws IOException {
        log.info("Creating new player...");

        TeamDAO teamDAO = teamManager.findById(teamId)
                .orElseThrow(TeamNotFoundException::new);
        Team team = teamMapper.mapToDomain(teamDAO, new CycleAvoidingMappingContext());
        Player player = PlayerAdminBuilders.buildFromRequest(request, team);

        if (file != null) {
            String storageKey = storageService.generateStorageKey(UUID.randomUUID(), file, FOLDER_NAME);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(file.getBytes());

            storageService.uploadFile(storageKey, file.getContentType(), baos);
            player.setPhoto(storageKey);
        }
        PlayerDAO playerDAO = playerMapper.mapToEntity(player, new CycleAvoidingMappingContext());
        playerDAO = playerManager.saveToDatabase(playerDAO);

        return playerDAO.getId();
    }
}
