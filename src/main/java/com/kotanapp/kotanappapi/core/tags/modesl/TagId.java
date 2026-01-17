package com.kotanapp.kotanappapi.core.tags.modesl;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Value
public class TagId {
    UUID id;
}
