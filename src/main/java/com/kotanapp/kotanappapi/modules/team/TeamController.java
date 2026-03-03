package com.kotanapp.kotanappapi.modules.team;

import com.kotanapp.kotanappapi.modules.team.models.TeamPageResponse;
import com.kotanapp.kotanappapi.modules.team.models.TeamRequest;
import com.kotanapp.kotanappapi.modules.team.models.TeamResponse;
import com.kotanapp.kotanappapi.modules.team.services.*;
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
@RequestMapping("/api/club/{clubId}/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamCreateService teamCreateService;
    private final TeamEditService teamEditService;
    private final TeamUploadCoverService teamUploadCoverService;
    private final TeamPageService teamPageService;
    private final TeamGetService teamGetService;
    private final TeamDeleteService teamDeleteService;
    private final TeamImportService teamImportService;

    private final static String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"", "/"}, consumes = {"multipart/form-data"})
    @Operation(
            description = "Create new team for a club",
            summary = "Create new team for a club"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> createTeam(
            @PathVariable(name = "clubId") UUID clubId,
            @RequestPart(name = "request") TeamRequest request,
            @RequestPart(name = "file") MultipartFile file
    ) throws IOException {
        teamCreateService.createTeam(clubId, request, file);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{teamId}")
    @Operation(
            description = "Edit team information",
            summary = "Edit team information"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> editTeam(
            @PathVariable(name = "clubId") UUID clubId,
            @PathVariable(name = "teamId") UUID teamId,
            @RequestBody @Valid TeamRequest request
    ) {
        teamEditService.updateTeam(clubId, teamId, request);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @PatchMapping(value = "/{teamId}/cover/upload", consumes = {"multipart/form-data"})
    @Operation(
            description = "Upload team cover image",
            summary = "Upload team cover image"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> uploadTeamCoverImage(
            @PathVariable(name = "clubId") UUID clubId,
            @PathVariable(name = "teamId") UUID teamId,
            @RequestPart(name = "file") MultipartFile file
    ) throws IOException {
        teamUploadCoverService.uploadCover(clubId, teamId, file);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    @Operation(
            description = "List teams from club",
            summary = "List teams from club"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<TeamPageResponse>> listTeams(
            @PathVariable(name = "clubId") UUID clubId
    ) {
        TeamPageResponse response = teamPageService.findAll(clubId);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/{teamId}")
    @Operation(
            description = "Get team by ID",
            summary = "Get team by ID"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<TeamResponse>> getTeam(
            @PathVariable(name = "clubId") UUID clubId,
            @PathVariable(name = "teamId") UUID teamId
    ) {
        TeamResponse response = teamGetService.getTeam(clubId, teamId);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{teamId}")
    @Operation(
            description = "Delete team",
            summary = "Delete team"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> deleteTeam(
            @PathVariable(name = "clubId") UUID clubId,
            @PathVariable(name = "teamId") UUID teamId
    ) {
        teamDeleteService.deleteTeam(clubId, teamId);
        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping(value = "/import", consumes = {"multipart/form-data"})
    @Operation(
            description = "Import teams from CSV",
            summary = "Import teams from CSV"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> importTeams(
            @RequestPart(name = "file") MultipartFile file
    ) throws IOException {
        teamImportService.importTeams(file);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
