package com.enigma.wmb.specification;

import ch.qos.logback.core.util.StringUtil;
import com.enigma.wmb.constant.MenuCategory;
import com.enigma.wmb.dto.request.SearchMenuRequest;
import com.enigma.wmb.entity.Menu;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuSpecification {
    public static Specification<Menu> getSpecification(SearchMenuRequest searchMenuRequest) {
        return new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                if (StringUtils.hasText(searchMenuRequest.getQuery())){
                    Predicate queryPredicate = criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + searchMenuRequest.getQuery() + "%"),
                            criteriaBuilder.equal(root.get("category"), MenuCategory.findByName(searchMenuRequest.getQuery()))
                    );
                    predicates.add(queryPredicate);
                }

                if (searchMenuRequest.getMinPrice() != null && searchMenuRequest.getMaxPrice() != null){
                    Predicate minMaxPredicate = criteriaBuilder.between(root.get("price"), searchMenuRequest.getMinPrice(), searchMenuRequest.getMaxPrice());
                    predicates.add(minMaxPredicate);
                } else if (searchMenuRequest.getMinPrice() != null) {
                    Predicate minPredicate = criteriaBuilder.between(root.get("price"), searchMenuRequest.getMinPrice(), Long.MAX_VALUE);
                    predicates.add(minPredicate);
                } else if (searchMenuRequest.getMaxPrice() != null) {
                    Predicate maxPredicate = criteriaBuilder.between(root.get("price"), 0L, searchMenuRequest.getMaxPrice());
                    predicates.add(maxPredicate);
                }

                if(predicates.isEmpty()) return criteriaBuilder.conjunction();
                return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            }
        };
    }
}
