package com.kotanapp.kotanappapi.modules.club.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import org.springframework.data.jpa.domain.Specification;

public class ClubSpecifications {
    public static Specification<ClubDAO> isNotArchived(){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("isArchived"), false);
        });
    }

    public static Specification<ClubDAO> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + name.toLowerCase() + "%"
            );
        };
    }

    public static Specification<ClubDAO> isOurClub(Boolean isOurClub){
        return ((root, query, criteriaBuilder) -> {
            if (isOurClub == null) {
                return criteriaBuilder.conjunction();
            }

            return  criteriaBuilder.equal(root.get("isOurClub"), isOurClub);
        });
    }
}
