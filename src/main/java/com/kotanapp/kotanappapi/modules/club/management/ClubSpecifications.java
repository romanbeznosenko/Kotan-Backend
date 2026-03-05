package com.kotanapp.kotanappapi.modules.club.management;

import com.kotanapp.kotanappapi.modules.club.models.ClubDAO;
import com.kotanapp.kotanappapi.utils.enums.AgeGroupEnum;
import com.kotanapp.kotanappapi.utils.enums.GenderEnum;
import org.springframework.data.jpa.domain.Specification;

public class ClubSpecifications {
    public static Specification<ClubDAO> notIsArchived(){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isArchived"), false));
    }

    public static Specification<ClubDAO> byName(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
    }

    public static Specification<ClubDAO> byGender(GenderEnum gender){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("gender"), gender);
    }

    public static Specification<ClubDAO> byAgeGroup(AgeGroupEnum ageGroup){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("ageGroup"), ageGroup);
    }
}
