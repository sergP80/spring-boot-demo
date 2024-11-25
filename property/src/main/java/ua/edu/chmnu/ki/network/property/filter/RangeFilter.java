package ua.edu.chmnu.ki.network.property.filter;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.function.Function;

@Setter
@Getter
public class RangeFilter<T extends Comparable<? super T>> {

    private T min;
    private T max;

    public RangeFilter() {}

    public RangeFilter(T min, T max) {
        this.min = min;
        this.max = max;
    }

    // Method to convert the range to a Predicate
    public Predicate toPredicate(Path<T> path, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction(); // Start with a "true" predicate
        if (min != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(path, min));
        }
        if (max != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(path, max));
        }
        return predicate;
    }

    // Method to convert the range to a Predicate
    public <U extends Comparable<? super U>> RangeFilter<U> convert(Function<T, U> minMapper, Function<T, U> maxMapper) {
        return new RangeFilter<>(
                min != null ? minMapper.apply(min) : null,
                max != null ? maxMapper.apply(max): null
        );
    }

    // Method to convert the range to a Predicate, accepting an expression function
    public Predicate toPredicate(Path<T> path, CriteriaBuilder criteriaBuilder, Function<Path<T>, Expression<T>> expressionFunction) {
        Predicate predicate = criteriaBuilder.conjunction();

        // Use the expression function to create the expression from the path
        Expression<T> expression = expressionFunction.apply(path);

        if (min != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(expression, min));
        }
        if (max != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(expression, max));
        }
        return predicate;
    }
}

