package ua.edu.chmnu.ki.network.lib.service;

import ua.edu.chmnu.ki.network.lib.filter.EntityFiltered;

import java.util.List;

public interface SearchService<T, Filter extends EntityFiltered> {
    List<T> getAllBy(Filter filter);
}
