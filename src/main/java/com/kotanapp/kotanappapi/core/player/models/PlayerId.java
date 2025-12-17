package com.kotanapp.kotanappapi.core.player.models;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor(staticName = "of")
public class PlayerId {
    UUID id;
}
