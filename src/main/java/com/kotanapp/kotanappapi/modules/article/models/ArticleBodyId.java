package com.kotanapp.kotanappapi.modules.article.models;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor(staticName = "of")
public class ArticleBodyId {
    UUID id;
}
