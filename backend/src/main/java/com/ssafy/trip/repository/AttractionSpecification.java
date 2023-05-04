package com.ssafy.trip.repository;

import com.ssafy.trip.Entity.Attraction;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class AttractionSpecification {

    public static Specification<Attraction> findBySidoCode(Integer sido_code){
        return new Specification<Attraction>() {
            @Override
            public Predicate toPredicate(Root<Attraction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("sido_code"),sido_code);
            }
        };
    }

    public static Specification<Attraction> ContainingContentType(Integer content_type_id){
        return new Specification<Attraction>() {
            @Override
            public Predicate toPredicate(Root<Attraction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("content_type_id"),content_type_id);
            }
        };
    }

    public static Specification<Attraction> ContatingTitle(String keyword){
        return new Specification<Attraction>() {
            @Override
            public Predicate toPredicate(Root<Attraction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("title"),"%"+keyword+"%");
            }
        };
    }


}
