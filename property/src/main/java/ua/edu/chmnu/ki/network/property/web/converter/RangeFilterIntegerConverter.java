package ua.edu.chmnu.ki.network.property.web.converter;

import org.springframework.stereotype.Component;
import ua.edu.chmnu.ki.network.property.web.converter.RangeFilterConverter;

@Component
public class RangeFilterIntegerConverter extends RangeFilterConverter<Integer> {

    public RangeFilterIntegerConverter() {
        super(Integer::valueOf);
    }
}

