package ua.edu.chmnu.ki.network.lib.filter.dto;

import lombok.Data;
import ua.edu.chmnu.ki.network.lib.filter.EntityFilter;
import ua.edu.chmnu.ki.network.lib.filter.RangeFilter;

import java.time.LocalDate;
import java.time.Year;

@Data
public class BookedFilterDTO implements EntityFilter {
    private Integer id;

    private String client;

    private String book;

    private Integer borrowedAt;

    private RangeFilter<Year> yearRange;

    private RangeFilter<LocalDate> dateRange;

    private String search;

    private String sort;

    public boolean hasSearch() {
        return search != null && !search.isBlank();
    }

    public boolean hasId() {
        return id != null;
    }

    public boolean hasClient() {
        return client != null && !client.isBlank();
    }

    public boolean hasBook() {
        return book != null && !book.isBlank();
    }

    public boolean hasBorrowedAt() {
        return borrowedAt != null;
    }
}
