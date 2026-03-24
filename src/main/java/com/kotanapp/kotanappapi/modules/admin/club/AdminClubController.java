package com.kotanapp.kotanappapi.modules.admin.club;

import com.kotanapp.kotanappapi.modules.admin.club.models.ClubAdminRequest;
import com.kotanapp.kotanappapi.modules.admin.club.services.ClubAdminCreateService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/club")
@RequiredArgsConstructor
public class AdminClubController {
    private final ClubAdminCreateService clubAdminCreateService;

    private static final String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"", "/"}, consumes = "multipart/form-data")
    @Operation(
            description = "Create new club by admin",
            summary = "Create new club by admin"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<UUID>> createClub(
            @RequestPart(name = "file", required = false)MultipartFile file,
            @RequestPart(name = "request") @Valid ClubAdminRequest request
    ) throws IOException {
        UUID response = clubAdminCreateService.createClub(request, file);

        return new ResponseEntity<>(new CustomResponse<>(response, DEFAULT_RESPONSE, HttpStatus.CREATED)
                , HttpStatus.CREATED);
    }
}
