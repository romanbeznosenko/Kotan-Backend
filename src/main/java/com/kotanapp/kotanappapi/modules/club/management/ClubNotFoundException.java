package com.kotanapp.kotanappapi.modules.club.management;

public class ClubNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Club not found in the system.";
    public ClubNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
