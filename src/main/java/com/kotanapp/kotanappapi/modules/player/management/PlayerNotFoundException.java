package com.kotanapp.kotanappapi.modules.player.management;

public class PlayerNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Player not found in the system.";
    public PlayerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
