package com.kotanapp.kotanappapi.core.match;

import com.kotanapp.kotanappapi.core.match.models.MatchPageResponse;
import com.kotanapp.kotanappapi.core.match.models.MatchRequest;
import com.kotanapp.kotanappapi.core.match.services.MatchCreateService;
import com.kotanapp.kotanappapi.core.match.services.MatchListService;
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
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchCreateService matchCreateService;
    private final MatchListService matchListService;

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

    @GetMapping(value = "/list")
    @Operation(
            description = "List matches",
            summary = "List matches"
    )
    @PreAuthorize("permitAll()")
    public ResponseEntity<CustomResponse<MatchPageResponse>> listMatches(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "10") int limit
    ) {
        MatchPageResponse response = matchListService.listMatches(page, limit);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
