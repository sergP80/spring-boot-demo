package ua.edu.chmnu.ki.network.lib.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Author;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Book;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookSpecFilterMapper implements EntitySpecFilterMapper<BookFilterDTO, Book> {
    @Override
    public Specification<Book> mapTo(@NonNull BookFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {

            var searchPredicate = createSearchPredicate(root, criteriaBuilder, filter.getSearch());

            var paramsPredicate = createParamPredicate(root, criteriaBuilder, filter);

            return criteriaBuilder.and(searchPredicate, paramsPredicate);
        };
    }

    private Predicate createSearchPredicate(Root<Book> root, CriteriaBuilder criteriaBuilder, String search) {
        if (search == null || search.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        List<Predicate> predicates = new ArrayList<>();

        search = "%" + search.toLowerCase() + "%";

        predicates.add(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("id").as(String.class)), search)
        );

        predicates.add(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("pages").as(String.class)), search)
        );

        predicates.add(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("price").as(String.class)), search)
        );

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), search));

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), search));

        Join<Book, Author> joinAuthor = root.join("author", JoinType.LEFT);

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinAuthor.get("name")), search));

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private Predicate createParamPredicate(Root<Book> root, CriteriaBuilder criteriaBuilder, BookFilterDTO filter) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.conjunction());

        if (filter.hasId()) {
            predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
        }

        if (filter.hasPages()) {
            predicates.add(criteriaBuilder.equal(root.get("pages"), filter.getPages()));
        } else {
            Optional.ofNullable(filter.getPageRange())
                    .ifPresent(range -> predicates.add(range.toPredicate(root.get("pages"), criteriaBuilder)));

        }

        if (filter.hasPrice()) {
            predicates.add(criteriaBuilder.equal(root.get("price"), filter.getPrice()));
        } else {
            Optional.ofNullable(filter.getPriceRange())
                    .ifPresent(range -> predicates.add(range.toPredicate(root.get("price"), criteriaBuilder)));
        }

        if (filter.hasYear()) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.function("EXTRACT", Integer.class, criteriaBuilder.literal("YEAR"), root.get("issueDate")), filter.getYear()));
        } else {
            Optional.ofNullable(filter.getYearRange())
                    .ifPresent(range -> predicates.add(
                                    range.toPredicate(
                                            root.get("issueDate"),
                                            criteriaBuilder,
                                            path -> criteriaBuilder.function("EXTRACT", Integer.class, criteriaBuilder.literal("YEAR"), path)
                                    )
                            )
                    );
        }

        if (filter.hasTitle()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), filter.getTitle().toLowerCase()));
        }

        if (filter.hasDescription()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), filter.getDescription().toLowerCase()));
        }

        if (filter.hasAuthor()) {
            Join<Book, Author> joinAuthor = root.join("author", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinAuthor.get("name")), filter.getAuthor().toLowerCase()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
