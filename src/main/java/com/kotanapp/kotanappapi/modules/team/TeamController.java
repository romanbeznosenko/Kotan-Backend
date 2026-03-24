package com.kotanapp.kotanappapi.modules.team;

import com.kotanapp.kotanappapi.modules.player.models.PlayerSquadResponse;
import com.kotanapp.kotanappapi.modules.team.models.TeamListResponse;
import com.kotanapp.kotanappapi.modules.team.models.TeamResponse;
import com.kotanapp.kotanappapi.modules.team.models.TeamSimpleListResponse;
import com.kotanapp.kotanappapi.modules.team.services.TeamGetService;
import com.kotanapp.kotanappapi.modules.team.services.TeamListService;
import com.kotanapp.kotanappapi.modules.team.services.TeamListSquadService;
import com.kotanapp.kotanappapi.modules.team.services.TeamSimpleListService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public/club/{clubId}/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamSimpleListService teamSimpleListService;

    private static final String DEFAULT_RESPONSE = "Operation successful.";

    @GetMapping(value = "/list/simple")
    @Operation(
            description = "Simple list of all teams from the club",
            summary = "Simple list of all teams from the club"
    )
    public ResponseEntity<CustomResponse<List<TeamSimpleListResponse>>> simpleListTeam(
            @PathVariable(name = "clubId") UUID clubId
    ) {
        List<TeamSimpleListResponse> response = teamSimpleListService.simpleListTeams(clubId);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
