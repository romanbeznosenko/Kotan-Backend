package com.kotanapp.kotanappapi.core.team.models;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor(staticName = "of")
public class TeamId {
    UUID id;
}
