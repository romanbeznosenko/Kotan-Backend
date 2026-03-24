package com.kotanapp.kotanappapi.modules.player.management;

import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecifications {
    public static Specification<PlayerDAO> isNotArchived(){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.isTrue(criteriaBuilder.equal(root.get("isArchived"), false));
        });
    }

    public static Specification<PlayerDAO> byTeam(TeamDAO team){
        return ((root, query, criteriaBuilder) -> {
            if (team == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("team"), team);
        });
    }
}
