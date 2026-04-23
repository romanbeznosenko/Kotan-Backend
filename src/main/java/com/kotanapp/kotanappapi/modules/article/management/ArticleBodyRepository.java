package com.kotanapp.kotanappapi.modules.article.management;

import com.kotanapp.kotanappapi.modules.article.models.ArticleBodyDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ArticleBodyRepository extends JpaRepository<ArticleBodyDAO, UUID>, JpaSpecificationExecutor<ArticleBodyDAO> {
}