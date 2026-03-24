package com.kotanapp.kotanappapi.modules.player.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.modules.player.models.PlayerSquadResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerBuilders {
    public static PlayerSquadResponse buildSquadResponse(PlayerDAO playerDAO, StorageService storageService) {
        return PlayerSquadResponse.builder()
                .id(playerDAO.getId())
                .name(playerDAO.getFirstName() + " " + playerDAO.getLastName())
                .position(playerDAO.getPosition())
                .jerseyNumber(playerDAO.getJerseyNumber())
                .photo(storageService.createPresignedGetUrl(playerDAO.getPhoto()))
                .build();
    }
}
