package com.kotanapp.kotanappapi.modules.club;

import com.kotanapp.kotanappapi.modules.club.models.ClubRequest;
import com.kotanapp.kotanappapi.modules.club.services.ClubCreateService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/club")
@RequiredArgsConstructor
public class ClubController {
    private final ClubCreateService clubCreateService;

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
}
