package ua.edu.chmnu.ki.network.lib.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ua.edu.chmnu.ki.network.lib.filter.EntityFilter;
import ua.edu.chmnu.ki.network.lib.web.dto.PageDTO;

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
