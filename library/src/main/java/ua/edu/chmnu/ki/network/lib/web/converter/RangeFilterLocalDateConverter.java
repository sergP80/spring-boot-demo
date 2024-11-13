package ua.edu.chmnu.ki.network.lib.web.converter;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class RangeFilterLocalDateConverter extends RangeFilterConverter<LocalDate> {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public RangeFilterLocalDateConverter() {
        super(value -> LocalDate.parse(value, FORMATTER));
    }
}

