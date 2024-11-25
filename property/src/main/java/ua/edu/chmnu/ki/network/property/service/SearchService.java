package ua.edu.chmnu.ki.network.property.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ua.edu.chmnu.ki.network.property.filter.EntityFilter;
import ua.edu.chmnu.ki.network.property.web.dto.PageDTO;

import java.util.Collections;
import java.util.List;

public interface SearchService<T, Filter extends EntityFilter> {
    List<T> getAllBy(Filter filter);

    default List<T> getAllBy(Filter filter, Sort sort) {
        return Collections.emptyList();
    }

    default PageDTO<T> getAllBy(Filter filter, Pageable pageable) {
        return PageDTO.empty();
    }
}
