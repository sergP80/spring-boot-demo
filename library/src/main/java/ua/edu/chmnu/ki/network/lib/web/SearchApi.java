package ua.edu.chmnu.ki.network.lib.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ua.edu.chmnu.ki.network.lib.filter.EntityFilter;
import ua.edu.chmnu.ki.network.lib.web.dto.PageDTO;

import java.util.Collections;
import java.util.List;

public interface SearchApi<T, F extends EntityFilter> {

    default List<T> getAllBy(F filter) {
        return Collections.emptyList();
    }
    default List<T> getAllBy(F filter, Sort sort) {
        return Collections.emptyList();
    }

    default PageDTO<T> getAllBy(F filter, Pageable pageable) {
        return PageDTO.empty();
    }
}
