package com.kotanapp.kotanappapi.modules.competition.management;

import com.kotanapp.kotanappapi.modules.competition.models.CompetitionDAO;
import com.kotanapp.kotanappapi.utils.enums.CompetitionTypeEnum;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CompetitionSpecifications {
    public static Specification<CompetitionDAO> byName(String name) {
        return ((root, query, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        });
    }

    public static Specification<CompetitionDAO> bySeason(String season) {
        return ((root, query, criteriaBuilder) -> {
            if (season == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("season"), season);
        });
    }

    public static Specification<CompetitionDAO> byCompetitionType(List<CompetitionTypeEnum> competitionTypes) {
        return ((root, query, criteriaBuilder) -> {
            if (competitionTypes == null || competitionTypes.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return root.get("type").in(competitionTypes);
        });
    }

    public static Specification<CompetitionDAO> notIsArchived(){
        return ((root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("isArchived"), false);
        });
    }
}
