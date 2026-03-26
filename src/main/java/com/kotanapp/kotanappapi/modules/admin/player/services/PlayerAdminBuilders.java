package com.kotanapp.kotanappapi.modules.admin.player.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.player.models.PlayerAdminListResponse;
import com.kotanapp.kotanappapi.modules.admin.player.models.PlayerAdminRequest;
import com.kotanapp.kotanappapi.modules.player.models.Player;
import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.modules.player.models.PlayerId;
import com.kotanapp.kotanappapi.modules.team.models.Team;
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
                .birthDate(playerDAO.getDateOfBirth())
                .build();
    }

    public static Player buildFromRequest(PlayerAdminRequest request, Team team) {
        return Player.builder()
                .playerId(PlayerId.of(null))
                .team(team)
                .firstName(request.firstName())
                .lastName(request.lastName())
                .jerseyNumber(request.jerseyNumber())
                .position(request.position())
                .gender(request.gender())
                .dateOfBirth(request.birthDate())
                .isArchived(false)
                .build();
    }
}
