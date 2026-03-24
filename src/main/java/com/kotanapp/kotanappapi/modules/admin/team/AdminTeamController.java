package com.kotanapp.kotanappapi.modules.admin.team;

import com.kotanapp.kotanappapi.modules.admin.team.models.TeamAdminListResponse;
import com.kotanapp.kotanappapi.modules.admin.team.services.TeamAdminPageService;
import com.kotanapp.kotanappapi.utils.CustomPaginationResponse;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/club/{clubId}/team")
@RequiredArgsConstructor
public class AdminTeamController {
    private final TeamAdminPageService teamAdminPageService;

    private static final String DEFAULT_RESPONSE = "Operation successful.";

    @GetMapping(value = "/")
    @Operation(
            description = "Get all teams from club by admin",
            summary = "Get all teams from club by admin"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomPaginationResponse<TeamAdminListResponse>> pageTeams(
            @PathVariable(name = "clubId") UUID clubId,
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit,
            @RequestParam(name = "gender", required = false)GenderEnum genderEnum,
            @RequestParam(name = "ageGroup", required = false)AgeGroupEnum ageGroupEnum
    ) {
        CustomPaginationResponse<TeamAdminListResponse> response = teamAdminPageService.pageTeams(page, limit, clubId, genderEnum, ageGroupEnum);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
