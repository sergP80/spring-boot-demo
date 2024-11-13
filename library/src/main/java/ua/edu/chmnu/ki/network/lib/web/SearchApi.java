package ua.edu.chmnu.ki.network.lib.web;

import ua.edu.chmnu.ki.network.lib.filter.EntityFilter;

import java.util.Collections;
import java.util.List;

public interface SearchApi<T, F extends EntityFilter> {

    default List<T> getAllBy(F filter) {
        return Collections.emptyList();
    }
}
