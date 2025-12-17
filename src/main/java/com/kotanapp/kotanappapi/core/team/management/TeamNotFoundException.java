package com.kotanapp.kotanappapi.core.team.management;

public class TeamNotFoundException extends RuntimeException {
    private final static String DEFAULT_MESSAGE = "Team not found in the system";
    public TeamNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
