package ua.edu.chmnu.ki.network.lib.web.converter;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RangeFilterBigDecimalConverter extends RangeFilterConverter<BigDecimal> {

    public RangeFilterBigDecimalConverter() {
        super(BigDecimal::new);
    }
}

