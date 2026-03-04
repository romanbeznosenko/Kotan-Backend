package com.kotanapp.kotanappapi.modules.competition.management;

public class CompetitionNotFoundException extends RuntimeException {
    private final static String DEFAULT_MESSAGE = "Competition not found in the system.";
    public CompetitionNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
