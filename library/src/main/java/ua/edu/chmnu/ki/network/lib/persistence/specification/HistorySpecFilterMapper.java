package ua.edu.chmnu.ki.network.lib.persistence.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ua.edu.chmnu.ki.network.lib.filter.dto.HistoryFilterDTO;
import ua.edu.chmnu.ki.network.lib.filter.mapper.EntitySpecFilterMapper;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Book;
import ua.edu.chmnu.ki.network.lib.persistence.entity.Client;
import ua.edu.chmnu.ki.network.lib.persistence.entity.History;
import ua.edu.chmnu.ki.network.lib.persistence.enums.BookStatus;

import javax.persistence.criteria.*;
import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class HistorySpecFilterMapper implements EntitySpecFilterMapper<HistoryFilterDTO, History> {
    @Override
    public Specification<History> mapTo(@NonNull HistoryFilterDTO filter) {
        return (root, query, criteriaBuilder) -> {

            var searchPredicate = createSearchPredicate(root, criteriaBuilder, filter.getSearch());

            var paramsPredicate = createParamPredicate(root, criteriaBuilder, filter);

            return criteriaBuilder.and(searchPredicate, paramsPredicate);
        };
    }

    private Predicate createSearchPredicate(Root<History> root, CriteriaBuilder criteriaBuilder, String search) {
        if (search == null || search.isEmpty()) {
            return criteriaBuilder.conjunction();
        }

        List<Predicate> predicates = new ArrayList<>();

        search = "%" + search.toLowerCase() + "%";

        predicates.add(
                criteriaBuilder.like(criteriaBuilder.lower(root.get("id").as(String.class)), search)
        );


        Join<History, Client> joinClient = root.join("client", JoinType.LEFT);

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinClient.get("name")), search));

        try {
            BookStatus status = BookStatus.valueOf(search.toUpperCase()); // Convert search to enum if possible
            predicates.add(criteriaBuilder.equal(root.get("status"), status));
        } catch (IllegalArgumentException e) {
        }

        Join<History, Book> joinBook = root.join("book", JoinType.LEFT);

        predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinBook.get("title")), search));

        return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
    }

    private Predicate createParamPredicate(Root<History> root, CriteriaBuilder criteriaBuilder, HistoryFilterDTO filter) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(criteriaBuilder.conjunction());

        if (filter.hasId()) {
            predicates.add(criteriaBuilder.equal(root.get("id"), filter.getId()));
        }

        if (filter.hasBorrowedAt()) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.function("EXTRACT", Integer.class, criteriaBuilder.literal("YEAR"), root.get("borrowedAt")), filter.getBorrowedAt()));
        } else {
            Optional.ofNullable(filter.getBorrowedAtRange())
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

        Optional.ofNullable(filter.getDateRangeBor())
                .ifPresent(range -> predicates.add(
                                range.toPredicate(
                                        root.get("borrowedAt"),
                                        criteriaBuilder)
                        )
                );

        if (filter.hasReturnedAt()) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.function("EXTRACT", Integer.class, criteriaBuilder.literal("YEAR"), root.get("returnedAt")), filter.getReturnedAt()));
        } else {
            Optional.ofNullable(filter.getReturnedAtRange())
                    .map(range ->
                            range.convert(
                                    value -> value.atMonthDay(MonthDay.of(1, 31)),
                                    value -> value.atMonthDay(MonthDay.of(12, 31))
                            )
                    )
                    .ifPresent(range -> predicates.add(
                                    range.toPredicate(
                                            root.get("returnedAt"),
                                            criteriaBuilder
                                    )
                            )
                    );
        }

        Optional.ofNullable(filter.getDateRangeRet())
                .ifPresent(range -> predicates.add(
                                range.toPredicate(
                                        root.get("returnedAt"),
                                        criteriaBuilder)
                        )
                );


        if (filter.hasStatus()) {
            predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
        }

        if (filter.hasClient()) {
            Join<History, Client> joinClient = root.join("client", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinClient.get("name")), filter.getClient().toLowerCase()));
        }

        if (filter.hasBook()) {
            Join<History, Book> joinBook = root.join("book", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(joinBook.get("title")), filter.getBook().toLowerCase()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
