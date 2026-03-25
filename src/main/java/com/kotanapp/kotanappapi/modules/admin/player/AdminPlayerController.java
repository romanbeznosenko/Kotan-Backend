package com.kotanapp.kotanappapi.modules.admin.player;

import com.kotanapp.kotanappapi.modules.admin.player.models.PlayerAdminListResponse;
import com.kotanapp.kotanappapi.modules.admin.player.models.PlayerAdminRequest;
import com.kotanapp.kotanappapi.modules.admin.player.services.PlayerAdminCreateService;
import com.kotanapp.kotanappapi.modules.admin.player.services.PlayerAdminListService;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/player")
@RequiredArgsConstructor
public class AdminPlayerController {
    private final PlayerAdminListService playerAdminListService;
    private final PlayerAdminCreateService playerAdminCreateService;

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

    @PostMapping(value = {"", "/"}, consumes = "multipart/form-data")
    @Operation(
            description = "Create player by admin",
            summary = "Create player by admin"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<UUID>> createPlayer(
            @RequestParam(name = "teamId") UUID teamId,
            @RequestPart(name = "request") @Valid PlayerAdminRequest request,
            @RequestPart(name = "file", required = false) MultipartFile file
    ) throws IOException {
        UUID response = playerAdminCreateService.createPlayer(teamId, request, file);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
