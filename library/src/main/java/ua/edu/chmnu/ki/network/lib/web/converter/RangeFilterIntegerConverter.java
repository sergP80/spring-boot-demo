package ua.edu.chmnu.ki.network.lib.web.converter;

import org.springframework.stereotype.Component;

@Component
public class RangeFilterIntegerConverter extends RangeFilterConverter<Integer> {

    public RangeFilterIntegerConverter() {
        super(Integer::valueOf);
    }
}

