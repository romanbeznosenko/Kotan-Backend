package com.kotanapp.kotanappapi.modules.admin.player;

import com.kotanapp.kotanappapi.modules.admin.player.models.PlayerAdminListResponse;
import com.kotanapp.kotanappapi.modules.admin.player.services.PlayerAdminListService;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/player")
@RequiredArgsConstructor
public class AdminPlayerController {
    private final PlayerAdminListService playerAdminListService;

    private static final String DEFAULT_RESPONSE = "Operation successful.";

    @GetMapping(value = "/list")
    @Operation(
            description = "List players by admin",
            summary = "List players by admin"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomPaginationResponse<PlayerAdminListResponse>> getPlayerAdminList(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "teamId", required = false) UUID teamId,
            @RequestParam(name = "position", required = false) PositionEnum position,
            @RequestParam(name = "gender", required = false) GenderEnum gender
    ) {
        CustomPaginationResponse<PlayerAdminListResponse> response = playerAdminListService.listAllPlayers(
                page, limit, teamId, position, gender
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
