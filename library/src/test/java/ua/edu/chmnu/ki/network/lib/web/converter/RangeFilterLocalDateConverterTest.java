package ua.edu.chmnu.ki.network.lib.web.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.edu.chmnu.ki.network.lib.filter.RangeFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RangeFilterLocalDateConverterTest {

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final RangeFilterLocalDateConverter converter = new RangeFilterLocalDateConverter();

    public static Stream<Arguments> provideDateFixtures() {
        return Stream.of(
                Arguments.of(
                        "01.03.2020,02.05.2021",
                        LocalDate.parse("01.03.2020", FORMATTER),
                        LocalDate.parse("02.05.2021", FORMATTER)
                ),
                Arguments.of(
                        "01.02.2022,01.06.2022",
                        LocalDate.parse("01.02.2022", FORMATTER),
                        LocalDate.parse("01.06.2022", FORMATTER)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideDateFixtures")
    void shouldConvertStringRangeToYearRange(String source, LocalDate min, LocalDate max) {
        RangeFilter<LocalDate> rangeFilter = converter.convert(source);

        assertNotNull(rangeFilter);

        if (Objects.nonNull(min)) {
            assertNotNull(rangeFilter.getMin());

            assertEquals(min, rangeFilter.getMin());
        }

        if (Objects.nonNull(max)) {
            assertNotNull(rangeFilter.getMax());

            assertEquals(max, rangeFilter.getMax());
        }
    }

}