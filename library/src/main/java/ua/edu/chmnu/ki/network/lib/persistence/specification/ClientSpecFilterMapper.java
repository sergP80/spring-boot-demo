package ua.edu.chmnu.ki.network.lib.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ua.edu.chmnu.ki.network.lib.filter.dto.ClientFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Client;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientSpecFilterMapper implements EntitySpecFilterMapper<ClientFilterDTO, Client> {

    @Override
    public Specification<Client> mapTo(@NonNull ClientFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {

            var searchPredicate = createSearchPredicate(root, criteriaBuilder, filter.getSearch());

            var paramsPredicate = createParamPredicate(root, criteriaBuilder, filter);

            return criteriaBuilder.and(searchPredicate, paramsPredicate);
        };
    }

    private Predicate createSearchPredicate(Root<Client> root, CriteriaBuilder criteriaBuilder, String search) {
        if (search == null || search.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        List<Predicate> predicates = new ArrayList<>();

        search = "%" + search.toLowerCase() + "%";

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), search));

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), search));

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private Predicate createParamPredicate(Root<Client> root, CriteriaBuilder criteriaBuilder, ClientFilterDTO filter) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.conjunction());

        if (filter.hasName()) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("name")), filter.getName()));
        }

        if (filter.hasEmail()) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("email")), filter.getEmail()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
