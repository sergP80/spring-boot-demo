package ua.edu.chmnu.ki.network.property.web.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import ua.edu.chmnu.ki.network.property.filter.RangeFilter;

import java.util.function.Function;


public class RangeFilterConverter<T extends Comparable<? super T>> implements Converter<String, RangeFilter<T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RangeFilterConverter.class.getName());

    private final Function<String, T> minMapper;

    private final Function<String, T> maxMapper;

    public RangeFilterConverter(Function<String, T> minMapper, Function<String, T> maxMapper) {
        this.minMapper = minMapper;
        this.maxMapper = maxMapper;
    }

    public RangeFilterConverter(Function<String, T> mapper) {
        this(mapper, mapper);
    }

    @Override
    public RangeFilter<T> convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }

        try {
            String[] parts = source.split(",");
            T min = null;
            T max = null;

            if (parts.length > 0 && !parts[0].isEmpty()) {
                min = minMapper.apply(parts[0]);
            }
            if (parts.length > 1 && !parts[1].isEmpty()) {
                max = maxMapper.apply(parts[1]);
            }

            LOGGER.info("Success parsed [{}, {}]", min, max);

            return new RangeFilter<>(min, max);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);

            return null;
        }
    }
}


