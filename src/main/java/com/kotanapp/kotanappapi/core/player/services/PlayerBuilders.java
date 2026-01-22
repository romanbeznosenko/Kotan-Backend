package com.kotanapp.kotanappapi.core.player.services;

import com.kotanapp.kotanappapi.core.player.models.*;
import com.kotanapp.kotanappapi.files.services.StorageService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerBuilders {
    public static Player buildFromRequest(PlayerRequest playerRequest) {
        return Player.builder()
                .playerId(PlayerId.of(null))
                .firstName(playerRequest.firstName())
                .lastName(playerRequest.lastName())
                .avatar(null)
                .playerPosition(playerRequest.playerPosition())
                .team(null)
                .birthDate(playerRequest.birthDate())
                .fieldNumber(playerRequest.fieldNumber())
                .build();
    }

    public static PlayerListResponse buildListResponse(PlayerDAO playerDAO, StorageService storageService) {
        return PlayerListResponse.builder()
                .id(playerDAO.getId())
                .firstName(playerDAO.getFirstName())
                .lastName(playerDAO.getLastName())
                .playerPosition(playerDAO.getPlayerPosition())
                .number(playerDAO.getFieldNumber())
                .avatar(storageService.createPresignedGetUrl(playerDAO.getAvatar()))
                .build();
    }

    public static PlayerResponse buildResponse(PlayerDAO playerDAO, StorageService storageService) {
        return PlayerResponse.builder()
                .playerId(playerDAO.getId())
                .firstName(playerDAO.getFirstName())
                .lastName(playerDAO.getLastName())
                .position(playerDAO.getPlayerPosition())
                .birthDate(playerDAO.getBirthDate())
                .fieldNumber(playerDAO.getFieldNumber())
                .avatar(storageService.createPresignedGetUrl(playerDAO.getAvatar()))
                .build();
    }
}
