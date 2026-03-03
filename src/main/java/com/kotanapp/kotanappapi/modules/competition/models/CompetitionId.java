package com.kotanapp.kotanappapi.modules.competition.models;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor(staticName = "of")
public class CompetitionId {
    UUID id;
}
