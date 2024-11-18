package ua.edu.chmnu.ki.network.lib.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.dto.BookedFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Author;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Book;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Booked;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Client;
import ua.edu.chmnu.ki.network.lib.persistence.enums.BookStatus;

import javax.persistence.criteria.*;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookedSpecFilterMapper implements EntitySpecFilterMapper<BookedFilterDTO, Booked>  {
    @Override
    public Specification<Booked> mapTo(@NonNull BookedFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {

            var searchPredicate = createSearchPredicate(root, criteriaBuilder, filter.getSearch());

            var paramsPredicate = createParamPredicate(root, criteriaBuilder, filter);

            return criteriaBuilder.and(searchPredicate, paramsPredicate);
        };
    }

    private Predicate createSearchPredicate(Root<Booked> root, CriteriaBuilder criteriaBuilder, String search) {
        if (search == null || search.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        List<Predicate> predicates = new ArrayList<>();

        search = "%" + search.toLowerCase() + "%";

        predicates.add(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("id").as(String.class)), search)
        );

        Join<Booked, Client> joinClient = root.join("client", JoinType.LEFT);

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinClient.get("name")), search));

        Join<Booked, Book> joinBook = root.join("book", JoinType.LEFT);

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinBook.get("title")), search));

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private Predicate createParamPredicate(Root<Booked> root, CriteriaBuilder criteriaBuilder, BookedFilterDTO filter) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.conjunction());

        if (filter.hasId()) {
            predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
        }

        if (filter.hasBorrowedAt()) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.function("EXTRACT", Integer.class, criteriaBuilder.literal("YEAR"), root.get("borrowedAt")), filter.getBorrowedAt()));
        } else {
            Optional.ofNullable(filter.getYearRange())
                    .map(range ->
                            range.convert(
                                    value -> value.atMonthDay(MonthDay.of(1, 31)),
                                    value -> value.atMonthDay(MonthDay.of(12, 31))
                            )
                    )
                    .ifPresent(range -> predicates.add(
                                    range.toPredicate(
                                            root.get("borrowedAt"),
                                            criteriaBuilder
                                    )
                            )
                    );
        }

        Optional.ofNullable(filter.getDateRange())
                .ifPresent(range -> predicates.add(
                                range.toPredicate(
                                        root.get("borrowedAt"),
                                        criteriaBuilder)
                        )
                );

        if (filter.hasClient()) {
            Join<Booked, Client> joinClient = root.join("client", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinClient.get("name")), filter.getClient().toLowerCase()));
        }

        if (filter.hasBook()) {
            Join<Booked, Book> joinBook = root.join("book", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinBook.get("title")), filter.getBook().toLowerCase()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
