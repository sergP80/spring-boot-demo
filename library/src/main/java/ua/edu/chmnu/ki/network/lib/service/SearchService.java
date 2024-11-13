package ua.edu.chmnu.ki.network.lib.service;

import ua.edu.chmnu.ki.network.lib.filter.EntityFilter;

import java.util.List;

public interface SearchService<T, Filter extends EntityFilter> {
    List<T> getAllBy(Filter filter);
}
