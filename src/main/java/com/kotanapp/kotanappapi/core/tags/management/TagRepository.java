package com.kotanapp.kotanappapi.core.tags.management;

import com.kotanapp.kotanappapi.core.tags.models.TagDAO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository extends JpaRepository<TagDAO, UUID> {
}