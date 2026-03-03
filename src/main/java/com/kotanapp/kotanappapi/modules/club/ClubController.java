package com.kotanapp.kotanappapi.modules.club;

import com.kotanapp.kotanappapi.modules.club.models.ClubPageResponse;
import com.kotanapp.kotanappapi.modules.club.models.ClubRequest;
import com.kotanapp.kotanappapi.modules.club.models.ClubResponse;
import com.kotanapp.kotanappapi.modules.club.services.*;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubController {
    private final ClubCreateService clubCreateService;
    private final ClubEditService clubEditService;
    private final ClubUploadLogoService clubUploadLogoService;
    private final ClubPageService clubPageService;
    private final ClubGetService clubGetService;

    private final static String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"", "/"}, consumes = {"multipart/form-data"})
    @Operation(
            description = "Create new club",
            summary = "Create new club"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> createClub(
            @RequestPart(name = "file") MultipartFile file,
            @RequestPart(name = "request") ClubRequest request
    ) throws IOException {
        clubCreateService.createClub(request, file);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PatchMapping(value = {"/{clubId}"})
    @Operation(
            description = "Edit club information",
            summary = "Edit club information"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> editClub(
            @PathVariable(name = "clubId")UUID clubId,
            @RequestBody @Valid ClubRequest request
    ) {
        clubEditService.editClub(clubId, request);
        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @PatchMapping(value = "/{clubId}/logo", consumes = {"multipart/form-data"})
    @Operation(
            description = "Upload club logo",
            summary = "Upload club logo"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> uploadClubLogo(
            @PathVariable(name = "clubId") UUID clubId,
            @RequestPart(name = "file") MultipartFile file
    ) throws IOException {
        clubUploadLogoService.uploadLogo(clubId, file);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    @Operation(
            description = "List all clubs",
            summary = "List all clubs"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<ClubPageResponse>> findAllClubs() {
        ClubPageResponse response = clubPageService.listAllClubs();
        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping(value = "/{clubId}")
    @Operation(
            description = "Get club",
            summary = "Get club"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<ClubResponse>> findClubById(
            @PathVariable(name = "clubId") UUID clubId
    ) {
        ClubResponse response = clubGetService.getClub(clubId);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}

