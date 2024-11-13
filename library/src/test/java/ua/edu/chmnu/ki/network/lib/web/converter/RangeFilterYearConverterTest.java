package ua.edu.chmnu.ki.network.lib.web.converter;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ua.edu.chmnu.ki.network.lib.filter.RangeFilter;

import java.time.Year;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RangeFilterYearConverterTest {

    private final RangeFilterYearConverter converter = new RangeFilterYearConverter();

    @ParameterizedTest
    @CsvSource(
            delimiter = '|',
            value = {
                    "2020,2021 | 2020 | 2021",
                    "2019,2022 | 2019 | 2022"
            })
    void shouldConvertStringRangeToYearRange(String source, Integer min, Integer max) {
        RangeFilter<Year> rangeFilter = converter.convert(source);

        assertNotNull(rangeFilter);

        if (Objects.nonNull(min)) {
            assertNotNull(rangeFilter.getMin());

            assertEquals(min, rangeFilter.getMin().getValue());
        }

        if (Objects.nonNull(max)) {
            assertNotNull(rangeFilter.getMax());

            assertEquals(max, rangeFilter.getMax().getValue());
        }
    }
}