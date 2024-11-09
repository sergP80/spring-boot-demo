package ua.edu.chmnu.ki.network.lib.filter.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;
import ua.edu.chmnu.ki.network.lib.filter.EntityFiltered;

@Data
public class CatalogFilterDTO implements EntityFiltered {

    private String index;

    private String name;

    private String search;

    private Sort sort;

    public boolean hasSearch() {
        return search != null && !search.isBlank();
    }

    public boolean hasIndex() {
        return index != null && !index.isBlank();
    }

    public boolean hasName() {
        return name != null && !name.isBlank();
    }

    public Sort getSort() {
        return sort == null ? Sort.by(Sort.Direction.ASC, "id") : sort;
    }
}
