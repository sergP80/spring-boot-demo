package ua.edu.chmnu.ki.network.lib.web;

import java.util.List;

public interface ResourceFilterApi<T, Filter> {

    List<T> getAllBy(Filter filter);
}
