package com.kotanapp.kotanappapi.core.player.services;

import com.kotanapp.kotanappapi.core.player.models.Player;
import com.kotanapp.kotanappapi.core.player.models.PlayerId;
import com.kotanapp.kotanappapi.core.player.models.PlayerRequest;
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
}
