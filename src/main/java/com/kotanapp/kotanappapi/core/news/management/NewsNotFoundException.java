package com.kotanapp.kotanappapi.core.news.management;

public class NewsNotFoundException extends RuntimeException {
    private final static String DEFAULT_MESSAGE = "News not found in the system.";
    public NewsNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
