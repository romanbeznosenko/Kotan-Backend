package com.kotanapp.kotanappapi.core.player.services;

import com.kotanapp.kotanappapi.core.player.models.*;
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
                .build();
    }

    public static PlayerListResponse buildListResponse(PlayerDAO playerDAO) {
        return PlayerListResponse.builder()
                .id(playerDAO.getId())
                .firstName(playerDAO.getFirstName())
                .lastName(playerDAO.getLastName())
                .playerPosition(playerDAO.getPlayerPosition())
                .number(null)
                .build();
    }
}
