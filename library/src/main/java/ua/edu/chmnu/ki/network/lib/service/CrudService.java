package ua.edu.chmnu.ki.network.lib.service;

public interface CrudService<T> {
    T getById(Integer id);

    boolean existById(Integer id);

    T create(T source);

    T updateById(Integer id, T source);

    void deleteById(Integer id);
}
