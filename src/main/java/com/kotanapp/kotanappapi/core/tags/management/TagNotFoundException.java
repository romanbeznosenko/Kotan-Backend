package com.kotanapp.kotanappapi.core.tags.management;

public class TagNotFoundException extends RuntimeException {
    private final static String DEFAULT_MESSAGE = "Tag bot found in the system.";
    public TagNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
