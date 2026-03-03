package com.kotanapp.kotanappapi.modules.team;

import com.kotanapp.kotanappapi.modules.team.models.TeamRequest;
import com.kotanapp.kotanappapi.modules.team.services.TeamCreateService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
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
}
