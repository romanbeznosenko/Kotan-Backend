package com.kotanapp.kotanappapi.utils;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Schema(description = "Wrapper class for custom pagination API responses, containing data and total count in database.")
public class CustomPaginationResponse<T> {

    @Schema(description = "List of found elements.")
    private List<T> data;

    @Schema(description = "Total amount of elements in database", example = "123")
    private Long count;
}
