package com.kotanapp.kotanappapi.modules.team.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import org.springframework.data.jpa.domain.Specification;

public class TeamSpecifications {
    public static Specification<TeamDAO> byClub(ClubDAO clubDAO) {
        return ((root, query, criteriaBuilder) -> {
            if (clubDAO == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("club"), clubDAO);
        });
    }

    public static Specification<TeamDAO> isNotArchived(){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("isArchived")));
    }
}
