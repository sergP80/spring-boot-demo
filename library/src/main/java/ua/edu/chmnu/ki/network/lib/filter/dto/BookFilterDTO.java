package ua.edu.chmnu.ki.network.lib.filter.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;
import ua.edu.chmnu.ki.network.lib.filter.EntityFiltered;
import ua.edu.chmnu.ki.network.lib.filter.RangeFilter;

import java.math.BigDecimal;

@Data
public class BookFilterDTO implements EntityFiltered {

    private Integer id;

    private String title;

    private String description;

    private String author;

    private BigDecimal price;

    private RangeFilter<BigDecimal> priceRange;

    private Integer pages;

    private RangeFilter<Integer> pageRange;

    private Integer year;

    private RangeFilter<Integer> yearRange;

    private String search;

    private Sort sort;

    public boolean hasSearch() {
        return search != null && !search.isBlank();
    }

    public boolean hasId() {
        return id != null;
    }

    public boolean hasTitle() {
        return title != null && !title.isBlank();
    }

    public boolean hasDescription() {
        return description != null && !description.isBlank();
    }

    public boolean hasAuthor() {
        return author != null && !author.isBlank();
    }

    public boolean hasPrice() {
        return price != null;
    }

    public boolean hasPages() {
        return pages != null;
    }

    public boolean hasYear() {
        return year != null;
    }



    public Sort getSort() {
        return sort == null ? Sort.by(Sort.Direction.ASC, "id") : sort;
    }
}
