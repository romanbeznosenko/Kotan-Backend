package com.kotanapp.kotanappapi.core.news.management;

import com.kotanapp.kotanappapi.core.news.models.NewsDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NewsRepository extends JpaRepository<NewsDAO, UUID> {
}