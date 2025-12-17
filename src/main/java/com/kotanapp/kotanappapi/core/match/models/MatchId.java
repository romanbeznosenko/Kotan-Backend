package com.kotanapp.kotanappapi.core.match.models;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor(staticName = "of")
public class MatchId {
    UUID id;
}
