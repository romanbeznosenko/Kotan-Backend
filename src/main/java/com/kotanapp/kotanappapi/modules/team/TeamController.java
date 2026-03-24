package com.kotanapp.kotanappapi.modules.team;

import com.kotanapp.kotanappapi.modules.team.models.TeamListResponse;
import com.kotanapp.kotanappapi.modules.team.models.TeamResponse;
import com.kotanapp.kotanappapi.modules.team.services.TeamGetService;
import com.kotanapp.kotanappapi.modules.team.services.TeamListService;
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
@RequestMapping("/api/public/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamListService teamListService;
    private final TeamGetService teamGetService;

    private static final String DEFAULT_RESPONSE = "Operation successful.";

    @GetMapping(value = "/list")
    @Operation(
            description = "List teams",
            summary = "List teams"
    )
    public ResponseEntity<CustomResponse<List<TeamListResponse>>> listTeams(
            @RequestParam(name = "gender", required = false) List<GenderEnum> genderEnumList,
            @RequestParam(name = "age", required = false) List<AgeGroupEnum> ageGroupEnumList
    ) {
        List<TeamListResponse> response = teamListService.getTeamList(genderEnumList, ageGroupEnumList);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/{teamId}")
    @Operation(
            description = "Get team by ID",
            summary = "Get team by ID"
    )
    public ResponseEntity<CustomResponse<TeamResponse>> getTeamById(
            @PathVariable(name = "teamId") UUID teamId
    ) {
        TeamResponse teamResponse = teamGetService.getTeamById(teamId);

        return new ResponseEntity<>(new CustomResponse<>(teamResponse, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
