package com.kotanapp.kotanappapi.modules.team.management;

public class TeamNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Team not found in the system.";
    public TeamNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
