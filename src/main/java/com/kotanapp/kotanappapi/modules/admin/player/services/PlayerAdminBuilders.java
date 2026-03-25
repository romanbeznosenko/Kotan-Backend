package com.kotanapp.kotanappapi.modules.admin.player.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.player.models.PlayerAdminListResponse;
import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerAdminBuilders {
    public static PlayerAdminListResponse buildListResponse(PlayerDAO playerDAO, StorageService storageService) {
        return PlayerAdminListResponse.builder()
                .id(playerDAO.getId())
                .firstName(playerDAO.getFirstName())
                .lastName(playerDAO.getLastName())
                .jerseyNumber(playerDAO.getJerseyNumber())
                .position(playerDAO.getPosition())
                .photo(storageService.createPresignedGetUrl(playerDAO.getPhoto()))
                .build();

    }
}
