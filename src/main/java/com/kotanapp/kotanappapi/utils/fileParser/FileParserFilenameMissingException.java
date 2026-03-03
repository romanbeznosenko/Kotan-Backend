package com.kotanapp.kotanappapi.utils.fileParser;

public class FileParserFilenameMissingException extends RuntimeException {
    private final static String DEFAULT_MESSAGE = "File name is missing.";
    public FileParserFilenameMissingException() {
        super(DEFAULT_MESSAGE);
    }
}
