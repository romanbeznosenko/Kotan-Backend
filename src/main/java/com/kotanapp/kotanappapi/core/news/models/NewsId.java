package com.kotanapp.kotanappapi.core.news.models;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@AllArgsConstructor(staticName = "of")
@Value
public class NewsId {
    UUID id;
}
