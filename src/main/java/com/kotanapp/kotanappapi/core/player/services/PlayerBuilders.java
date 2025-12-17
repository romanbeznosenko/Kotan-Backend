package com.kotanapp.kotanappapi.core.player.services;

import com.kotanapp.kotanappapi.core.player.models.Player;
import com.kotanapp.kotanappapi.core.player.models.PlayerId;
import com.kotanapp.kotanappapi.core.player.models.PlayerRequest;
import com.kotanapp.kotanappapi.core.team.models.Team;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PlayerBuilders {
    public static Player buildFromRequest(PlayerRequest playerRequest, Team team) {
        return Player.builder()
                .playerId(PlayerId.of(null))
                .firstName(playerRequest.firstName())
                .lastName(playerRequest.lastName())
                .avatar(null)
                .playerPosition(playerRequest.playerPosition())
                .team(team)
                .birthDate(playerRequest.birthDate())
                .build();
    }
}
