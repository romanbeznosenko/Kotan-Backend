package com.kotanapp.kotanappapi.core.player;

import com.kotanapp.kotanappapi.core.player.models.PlayerPageResponse;
import com.kotanapp.kotanappapi.core.player.models.PlayerRequest;
import com.kotanapp.kotanappapi.core.player.services.PlayerCreateService;
import com.kotanapp.kotanappapi.core.player.services.PlayerEditService;
import com.kotanapp.kotanappapi.core.player.services.PlayerListTeamService;
import com.kotanapp.kotanappapi.core.player.services.PlayerUpdateAvatarService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
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
@RequestMapping("/api/team/{teamId}/player")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerCreateService playerCreateService;
    private final PlayerListTeamService playerListTeamService;
    private final PlayerUpdateAvatarService playerUpdateAvatarService;
    private final PlayerEditService playerEditService;

    private final static String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"/", ""})
    @Operation(
            description = "Create player",
            summary = "Create player"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> createPlayer(
            @PathVariable(name = "teamId") UUID teamId,
            @RequestBody @Valid PlayerRequest request
    ) {
        playerCreateService.createPlayer(teamId, request);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    @Operation(
            description = "List team players",
            summary = "List team players"
    )
    @PreAuthorize("permitAll()")
    public ResponseEntity<CustomResponse<PlayerPageResponse>> listTeamPlayers(
            @PathVariable(name = "teamId") UUID teamId
    ) {
        PlayerPageResponse response = playerListTeamService.listPlayersByTeam(teamId);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @PutMapping(value = "/{playerId}/avatar", consumes = {"multipart/form-data"})
    @Operation(
            description = "Update player's avatar",
            summary = "Update player's avatar"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> updatePlayerAvatar(
            @PathVariable(name = "playerId") UUID playerId,
            @RequestPart(name = "file") MultipartFile file
            ) throws IOException {
        playerUpdateAvatarService.updatePlayerAvatar(playerId, file);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @PatchMapping(value = "/{playerId}")
    @Operation(
            description = "Edit player information",
            summary = "Edit player information"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> editPlayer(
            @PathVariable(name = "playerId") UUID playerId,
            @RequestBody @Valid PlayerRequest request
    ) {
        playerEditService.editPlayer(playerId, request);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
