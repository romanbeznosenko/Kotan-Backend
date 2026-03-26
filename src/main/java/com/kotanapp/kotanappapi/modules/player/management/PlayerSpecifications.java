package com.kotanapp.kotanappapi.modules.player.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.player.models.PlayerDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import com.kotanapp.kotanappapi.utils.enums.PositionEnum;
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

    public static Specification<PlayerDAO> byGender(GenderEnum gender){
        return ((root, query, criteriaBuilder) -> {
            if (gender == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("gender"), gender);
        });
    }

    public static Specification<PlayerDAO> byPosition(PositionEnum position){
        return ((root, query, criteriaBuilder) -> {
            if (position == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("position"), position);
        });
    }

    public static Specification<PlayerDAO> byClub(ClubDAO club){
        return ((root, query, criteriaBuilder) -> {
            if (club == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("team").get("club"), club);
        });
    }
}
