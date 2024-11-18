package ua.edu.chmnu.ki.network.lib.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import ua.edu.chmnu.ki.network.lib.filter.dto.CatalogFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;

import ua.edu.chmnu.ki.network.lib.persistence.entity.Catalog;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CatalogSpecFilterMapper implements EntitySpecFilterMapper<CatalogFilterDTO, Catalog> {
    @Override
    public Specification<Catalog> mapTo(@NonNull CatalogFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {

            var searchPredicate = createSearchPredicate(root, criteriaBuilder, filter.getSearch());

            var paramsPredicate = createParamPredicate(root, criteriaBuilder, filter);

            return criteriaBuilder.and(searchPredicate, paramsPredicate);
        };
    }

    private Predicate createSearchPredicate(Root<Catalog> root, CriteriaBuilder criteriaBuilder, String search) {
        if (search == null || search.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        List<Predicate> predicates = new ArrayList<>();

        search = "%" + search.toLowerCase() + "%";


        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("index")), search));

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), search));

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private Predicate createParamPredicate(Root<Catalog> root, CriteriaBuilder criteriaBuilder, CatalogFilterDTO filter) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.conjunction());

        if (filter.hasIndex()) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("index")), filter.getIndex()));
        }

        if (filter.hasName()) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")), filter.getName()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
