package com.kotanapp.kotanappapi.modules.article.management;

public class ArticleNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Article not found in the system.";
    public ArticleNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
