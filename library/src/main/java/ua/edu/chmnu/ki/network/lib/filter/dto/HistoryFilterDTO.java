package ua.edu.chmnu.ki.network.lib.filter.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;
import ua.edu.chmnu.ki.network.lib.filter.EntityFilter;
import ua.edu.chmnu.ki.network.lib.filter.RangeFilter;
import ua.edu.chmnu.ki.network.lib.persistence.enums.BookStatus;

import java.time.LocalDate;
import java.time.Year;

@Data
public class HistoryFilterDTO implements EntityFilter {

    private Integer id;

    private String client;

    private BookStatus status;

    private Integer borrowedAt;

    private RangeFilter<Year> borrowedAtRange;

    private RangeFilter<LocalDate> dateRangeBor;

    private Integer returnedAt;

    private RangeFilter<Year> returnedAtRange;

    private RangeFilter<LocalDate> dateRangeRet;

    private String book;

    private String search;

    private Sort sort;

    public boolean hasSearch() {
        return search != null && !search.isBlank();
    }

    public Sort getSort() {
        return sort == null ? Sort.by(Sort.Direction.ASC, "id") : sort;
    }

    public boolean hasId() {
        return id != null;
    }

    public boolean hasClient() {
        return client != null && !client.isBlank();
    }

    public boolean hasStatus() {
        return status != null;
    }

    public boolean hasBook() {
        return book != null && !book.isBlank();
    }

    public boolean hasBorrowedAt() {
        return borrowedAt != null;
    }

    public boolean hasReturnedAt() {return returnedAt != null; }
}
