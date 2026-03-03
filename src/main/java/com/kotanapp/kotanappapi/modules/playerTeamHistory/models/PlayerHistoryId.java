package com.kotanapp.kotanappapi.modules.playerTeamHistory.models;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor(staticName = "of")
public class PlayerHistoryId {
    UUID id;
}
