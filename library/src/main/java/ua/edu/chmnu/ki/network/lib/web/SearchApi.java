package ua.edu.chmnu.ki.network.lib.web;

import ua.edu.chmnu.ki.network.lib.filter.EntityFiltered;

import java.util.Collections;
import java.util.List;

public interface SearchApi<T, Filter extends EntityFiltered> {

    default List<T> getAllBy(Filter filter) {
        return Collections.emptyList();
    }
}
