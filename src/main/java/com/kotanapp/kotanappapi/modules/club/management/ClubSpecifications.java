package com.kotanapp.kotanappapi.modules.club.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import org.springframework.data.jpa.domain.Specification;

public class ClubSpecifications {
    public static Specification<ClubDAO> notIsArchived(){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isArchived"), false));
    }
}
