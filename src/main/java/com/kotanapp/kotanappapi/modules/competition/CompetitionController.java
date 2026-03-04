package com.kotanapp.kotanappapi.modules.competition;

import com.kotanapp.kotanappapi.modules.competition.models.CompetitionRequest;
import com.kotanapp.kotanappapi.modules.competition.services.CompetitionCreateService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/competition")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionCreateService competitionCreateService;

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
}
