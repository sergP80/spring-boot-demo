package ua.edu.chmnu.ki.network.lib.web.converter;

import org.springframework.core.convert.converter.Converter;
import ua.edu.chmnu.ki.network.lib.filter.RangeFilter;

import java.util.function.Function;

public class RangeFilterConverter<T extends Comparable<T>> implements Converter<String, RangeFilter<T>> {

    private final Function<String, T> mapper;

    public RangeFilterConverter(Function<String, T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public RangeFilter<T> convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }

        String[] parts = source.split(",");
        T min = null;
        T max = null;

        if (parts.length > 0 && !parts[0].isEmpty()) {
            min = mapper.apply(parts[0]);
        }
        if (parts.length > 1 && !parts[1].isEmpty()) {
            max = mapper.apply(parts[1]);
        }

        return new RangeFilter<>(min, max);
    }
}


