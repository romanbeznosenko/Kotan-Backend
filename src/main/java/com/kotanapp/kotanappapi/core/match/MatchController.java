package com.kotanapp.kotanappapi.core.match;

import com.kotanapp.kotanappapi.core.match.models.MatchRequest;
import com.kotanapp.kotanappapi.core.match.services.MatchCreateService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchCreateService matchCreateService;

    private final static String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"/", ""})
    @Operation(
            description = "Create match",
            summary = "Create match"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> createMatch(
            @RequestPart @Valid MatchRequest request,
            @RequestPart(name = "homeTeamId") UUID homeTeamId,
            @RequestPart(name = "awayTeamId") UUID awayTeamId
    ) {
        matchCreateService.createMatch(request, homeTeamId, awayTeamId);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
