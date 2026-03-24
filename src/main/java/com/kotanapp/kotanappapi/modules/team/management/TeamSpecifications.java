package com.kotanapp.kotanappapi.modules.team.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.modules.team.models.TeamDAO;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class TeamSpecifications {
    public static Specification<TeamDAO> byAgeGroup(List<AgeGroupEnum> ageGroupEnumList) {
        return (root, query, criteriaBuilder) -> {
            if (ageGroupEnumList == null || ageGroupEnumList.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return root.get("ageGroup").in(ageGroupEnumList);
        };
    }

    public static Specification<TeamDAO> byGender(List<GenderEnum> genderEnumList) {
        return ((root, query, criteriaBuilder) -> {
            if (genderEnumList == null || genderEnumList.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return root.get("gender").in(genderEnumList);
        });
    }

    public static Specification<TeamDAO> isNotArchived(){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("isArchived")));
    }

    public static Specification<TeamDAO> byClub(ClubDAO clubDAO){
        return ((root, query, criteriaBuilder) -> {
            if (clubDAO == null){
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("club"), clubDAO);
        });
    }
}