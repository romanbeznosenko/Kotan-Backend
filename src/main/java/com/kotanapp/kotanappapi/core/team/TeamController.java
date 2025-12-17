package com.kotanapp.kotanappapi.core.team;

import com.kotanapp.kotanappapi.core.team.models.TeamPageResponse;
import com.kotanapp.kotanappapi.core.team.models.TeamRequest;
import com.kotanapp.kotanappapi.core.team.services.TeamCreateService;
import com.kotanapp.kotanappapi.core.team.services.TeamListService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamCreateService teamCreateService;
    private final TeamListService teamListService;

    private final static String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"/", ""})
    @Operation(
            description = "Create team",
            summary = "Create team"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> createTeam(
            @RequestBody @Valid TeamRequest teamRequest
    ) {
        teamCreateService.create(teamRequest);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    @Operation(
            description = "List teams",
            summary = "List teams"
    )
    @PreAuthorize("permitAll()")
    public ResponseEntity<CustomResponse<TeamPageResponse>> list(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        TeamPageResponse response = teamListService.listTeams(page, limit);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
