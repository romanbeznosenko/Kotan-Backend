package com.kotanapp.kotanappapi.modules.admin.player.services;

import com.kotanapp.kotanappapi.files.services.StorageService;
import com.kotanapp.kotanappapi.modules.admin.player.models.PlayerAdminListResponse;
import com.kotanapp.kotanappapi.modules.player.management.PlayerManager;
import com.kotanapp.kotanappapi.modules.player.management.PlayerSpecifications;
import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.modules.team.management.TeamManager;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerAdminListService {
    private final TeamManager teamManager;
    private final PlayerManager playerManager;
    private final StorageService storageService;

    public CustomPaginationResponse<PlayerAdminListResponse> listAllPlayers(
            int page, int limit,
            UUID teamId,
            PositionEnum position,
            GenderEnum gender
    ){
        log.info("Fetching all players...");

        TeamDAO teamDAO = teamManager.findById(teamId)
                .orElse(null);

        Specification<PlayerDAO> spec = PlayerSpecifications.byTeam(teamDAO)
                .and(PlayerSpecifications.byGender(gender))
                .and(PlayerSpecifications.byPosition(position))
                .and(PlayerSpecifications.isNotArchived());

        Page<PlayerDAO> playerDAOPage = playerManager.findAll(spec, PageRequest.of(page - 1, limit));
        List<PlayerAdminListResponse> playerAdminListResponses = playerDAOPage.get()
                .map(playerDAO -> PlayerAdminBuilders.buildListResponse(playerDAO, storageService))
                .toList();


        return new CustomPaginationResponse<>(playerAdminListResponses, playerDAOPage.getTotalElements());
    }
}
