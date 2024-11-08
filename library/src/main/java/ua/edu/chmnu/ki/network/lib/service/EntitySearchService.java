package ua.edu.chmnu.ki.network.lib.service;

import java.util.List;

public interface EntitySearchService<T, Filter> {
    List<T> getAllBy(Filter filter);
}
