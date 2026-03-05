package com.kotanapp.kotanappapi.modules.competition;

import com.kotanapp.kotanappapi.modules.competition.models.CompetitionPageResponse;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionRequest;
import com.kotanapp.kotanappapi.modules.competition.models.CompetitionResponse;
import com.kotanapp.kotanappapi.modules.competition.services.*;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/competition")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionCreateService competitionCreateService;
    private final CompetitionEditService competitionEditService;
    private final CompetitionDeleteService competitionDeleteService;
    private final CompetitionPageService competitionPageService;
    private final CompetitionGetService competitionGetService;

    private final static String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"", "/"})
    @Operation(
            description = "Create new competition",
            summary = "Create new competition"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> create(
            @RequestBody @Valid CompetitionRequest request
    ) {
        competitionCreateService.createCompetition(request);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{competitionId}")
    @Operation(
            description = "Edit competition information",
            summary = "Edit competition information"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> edit(
            @PathVariable(name = "competitionId")UUID competitionId,
            @RequestBody @Valid CompetitionRequest request
    ) {
        competitionEditService.editCompetition(competitionId, request);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{competitionId}")
    @Operation(
            description = "Delete competition by Id",
            summary = "Delete competition by Id"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> delete(
            @PathVariable(name = "competitionId") UUID competitionId
    ) {
        competitionDeleteService.deleteCompetition(competitionId);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    @Operation(
            description = "List competitions",
            summary = "List competitions"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<CompetitionPageResponse>> list(){
        CompetitionPageResponse response = competitionPageService.pageCompetitions();
        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/{competitionId}")
    @Operation(
            description = "Get competition",
            summary = "Get competition"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<CompetitionResponse>> getCompetition(
            @PathVariable(name = "competitionId") UUID competitionId
    ) {
        CompetitionResponse response = competitionGetService.get(competitionId);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
