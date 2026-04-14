package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ArticleBodyRepository extends JpaRepository<ArticleBodyDAO, UUID> {
}