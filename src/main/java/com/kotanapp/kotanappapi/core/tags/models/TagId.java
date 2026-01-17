package com.kotanapp.kotanappapi.core.tags.models;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Value
public class TagId {
    UUID id;
}
