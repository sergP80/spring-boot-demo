package ua.edu.chmnu.ki.network.property.filter.dto;

import lombok.Data;
import ua.edu.chmnu.ki.network.property.filter.EntityFilter;
import ua.edu.chmnu.ki.network.property.filter.RangeFilter;

import java.math.BigDecimal;

@Data
public class PropertiesFilterDTO implements EntityFilter {
    private Integer id;

    private String location;

    private String type;

    private BigDecimal price;

    private BigDecimal priceMin;

    private BigDecimal priceMax;

    private RangeFilter<BigDecimal> priceRange;

    private String search;

    public boolean hasSearch() {
        return search != null && !search.isBlank();
    }

    public boolean hasId() {
        return id != null;
    }

    public boolean hasLocation() {
        return location != null && !location.isBlank();
    }

    public boolean hasType() {
        return type != null && !type.isBlank();
    }

    public boolean hasPrice() {
        return price != null;
    }
}

