package ua.edu.chmnu.ki.network.property.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ua.edu.chmnu.ki.network.property.filter.dto.PropertiesFilterDTO;
import ua.edu.chmnu.ki.network.property.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.property.persistence.entity.Properties;
import ua.edu.chmnu.ki.network.property.persistence.enums.Status;

import javax.persistence.criteria.*;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PropertiesSpecFilterMapper implements EntitySpecFilterMapper<PropertiesFilterDTO, Properties> {
    @Override
    public Specification<Properties> mapTo(@NonNull PropertiesFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {

            var searchPredicate = createSearchPredicate(root, criteriaBuilder, filter.getSearch());

            var paramsPredicate = createParamPredicate(root, criteriaBuilder, filter);

            // Apply filter for status
            /*Predicate statusPredicate = criteriaBuilder.equal(root.get("status"), Status.AVAILABLE);*/

            return criteriaBuilder.and(searchPredicate, paramsPredicate);
        };
    }

    private Predicate createSearchPredicate(Root<Properties> root, CriteriaBuilder criteriaBuilder, String search) {
        if (search == null || search.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        List<Predicate> predicates = new ArrayList<>();

        search = "%" + search.toLowerCase() + "%";

        predicates.add(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("id").as(String.class)), search)
        );

        predicates.add(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("price").as(String.class)), search)
        );

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), search));

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("type")), search));

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private Predicate createParamPredicate(Root<Properties> root, CriteriaBuilder criteriaBuilder, PropertiesFilterDTO filter) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.conjunction());

        if (filter.hasId()) {
            predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
        }


        if (filter.hasPrice()) {
            predicates.add(criteriaBuilder.equal(root.get("price"), filter.getPrice()));
        } else {
            Optional.ofNullable(filter.getPriceRange())
                    .ifPresent(range -> predicates.add(range.toPredicate(root.get("price"), criteriaBuilder)));
        }

        if (filter.hasLocation()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), filter.getLocation().toLowerCase()));
        }

        if (filter.hasType()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("type")), filter.getType().toLowerCase()));
        }



        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

