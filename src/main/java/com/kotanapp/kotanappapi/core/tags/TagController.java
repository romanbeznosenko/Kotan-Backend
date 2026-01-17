package com.kotanapp.kotanappapi.core.tags;

import com.kotanapp.kotanappapi.core.tags.models.TagListResponse;
import com.kotanapp.kotanappapi.core.tags.models.TagRequest;
import com.kotanapp.kotanappapi.core.tags.services.TagCreateService;
import com.kotanapp.kotanappapi.core.tags.services.TagListService;
import com.kotanapp.kotanappapi.utils.CustomResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagCreateService tagCreateService;
    private final TagListService tagListService;

    private final static String DEFAULT_RESPONSE = "Operation successful.";

    @PostMapping(value = {"", "/"})
    @Operation(
            description = "Create tag",
            summary = "Create tag"
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CustomResponse<Void>> createTag(
            @RequestBody @Valid TagRequest request
    ) {
        tagCreateService.createTag(request);

        return new ResponseEntity<>(new CustomResponse<>(null, DEFAULT_RESPONSE, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping(value = "/list")
    @Operation(
            description = "List all tags",
            summary = "List all tags"
    )
    @PreAuthorize("permitAll()")
    public ResponseEntity<CustomResponse<TagListResponse>> listTags() {
        TagListResponse tagListResponse = tagListService.listTags();

        return new ResponseEntity<>(new CustomResponse<>(tagListResponse, DEFAULT_RESPONSE, HttpStatus.OK), HttpStatus.OK);
    }
}
