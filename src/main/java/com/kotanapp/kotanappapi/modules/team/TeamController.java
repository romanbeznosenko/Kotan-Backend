package com.kotanapp.kotanappapi.modules.team;

import com.kotanapp.kotanappapi.modules.team.models.TeamListResponse;
import com.kotanapp.kotanappapi.modules.team.services.TeamListService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/public/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamListService teamListService;

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
}
