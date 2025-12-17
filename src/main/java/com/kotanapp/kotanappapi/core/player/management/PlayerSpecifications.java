package com.kotanapp.kotanappapi.core.player.management;

import com.kotanapp.kotanappapi.core.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.core.team.models.TeamDAO;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecifications {
    public static Specification<PlayerDAO> findByTeam(TeamDAO team) {
        return ((root, query, criteriaBuilder) -> {
            if (team == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("team"), team);
        });
    }
}
