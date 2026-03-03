package com.kotanapp.kotanappapi.utils.fileParser;

public class FileParserUnsupportedFileExtensionException extends RuntimeException {
    private final static String DEFAULT_MESSAGE = "File extension is not supported.";
    public FileParserUnsupportedFileExtensionException() {
        super(DEFAULT_MESSAGE);
    }
}
