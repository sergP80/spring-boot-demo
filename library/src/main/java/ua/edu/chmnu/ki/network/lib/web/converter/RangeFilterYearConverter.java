package ua.edu.chmnu.ki.network.lib.web.converter;

import org.springframework.stereotype.Component;

import java.time.Year;

@Component
public class RangeFilterYearConverter extends RangeFilterConverter<Year> {

    public RangeFilterYearConverter() {
        super(Year::parse);
    }
}

