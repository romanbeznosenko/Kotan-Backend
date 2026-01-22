package com.kotanapp.kotanappapi.core.player.management;

public class PlayerNotFoundException extends RuntimeException {
    private final static String DEFAULT_MESSAGE = "Player not found in the system.";
    public PlayerNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
