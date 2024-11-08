package ua.edu.chmnu.ki.network.lib.service;

import java.util.List;

public interface EntityService<T> {
    T getById(Integer id);

    List<T> getAll();

    boolean existById(Integer id);

    T create(T source);

    T updateById(Integer id, T source);
}
